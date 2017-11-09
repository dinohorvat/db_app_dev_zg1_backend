package database.application.development.service.impl;

import database.application.development.model.domain.Branch;
import database.application.development.model.domain.Employee;
import database.application.development.model.history.HstBranch;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.BranchDao;
import database.application.development.repository.hst.HstBranchDao;
import database.application.development.service.BranchService;
import database.application.development.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {
    
    private BranchDao branchDAO;
    private HstBranchDao hstBranchDao;
    private EmployeeService employeeService;

    @Autowired
    public BranchServiceImpl(BranchDao branchDAO, HstBranchDao hstBranchDao, EmployeeService employeeService){
        this.branchDAO = branchDAO;
        this.hstBranchDao = hstBranchDao;
        this.employeeService = employeeService;
    }

    @Override
    public Response<Branch> getBranchById(Request<ApplicationInputs> request) {
        Branch branch = branchDAO.getBranchById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), branch);
    }

    @Override
    public Response<Branch> createBranch(Request<ApplicationInputs> request) {
        Set<Employee> tempEmployees = null;

        if(request.getBody().getBranch().getEmployees() != null && request.getBody().getBranch().getEmployees().size() > 0){
             tempEmployees = request.getBody().getBranch().getEmployees();
            request.getBody().getBranch().setEmployees(null);
        }

        Branch branch = branchDAO.createBranch(request.getBody().getBranch());

        if(tempEmployees != null && tempEmployees.size() > 0){
            tempEmployees.forEach(employee -> {
                request.getBody().setEmployee(employee);
                branch.getEmployees().add((employeeService.createEmployee(request)).getBody());
            });
        }

        addToBranchHistory("INSERT", branch);
        return new Response<>(new OutputHeader(), branch);
    }

    @Override
    public Response<Branch> updateBranch(Request<ApplicationInputs> request) {
        Branch branch = branchDAO.updateBranch(request.getBody().getBranch());
        addToBranchHistory("UPDATE", branch);
        return new Response<>(new OutputHeader(), branch);
    }

    @Override
    public void deleteBranch(Request<ApplicationInputs> request) {
        Branch branch = branchDAO.getBranchById(request.getBody().getEntityId());
        addToBranchHistory("DELETE", branch);
        branchDAO.deleteBranch(branch);
    }

    /**
     * Adds a new row to the HST_COMPANY table for this branch object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param branch The {@link Branch} object which has been changed
     */
    private void addToBranchHistory(String changeDesc, Branch branch) {
        HstBranch hstBranch = new HstBranch(changeDesc, branch);
        hstBranch = hstBranchDao.createHstBranch(hstBranch);
        branch.getHstBranches().add(hstBranch);
    }
}
