package database.application.development.service.impl;

import database.application.development.model.domain.Branch;
import database.application.development.model.domain.Company;
import database.application.development.model.domain.RewardPolicy;
import database.application.development.model.history.HstCompany;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.CompanyDao;
import database.application.development.repository.hst.HstCompanyDao;
import database.application.development.service.BranchService;
import database.application.development.service.CompanyService;
import database.application.development.service.RewardPolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDAO;
    private HstCompanyDao hstCompanyDao;
    private BranchService branchService;
    private RewardPolicyService rewardPolicyService;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDAO, HstCompanyDao hstCompanyDao,
                              BranchService branchService, RewardPolicyService rewardPolicyService){
        this.companyDAO = companyDAO;
        this.hstCompanyDao = hstCompanyDao;
        this.branchService = branchService;
        this.rewardPolicyService = rewardPolicyService;
    }

    @Override
    public Response<Company> getCompanyById(Request<ApplicationInputs> request) {
        Company company = companyDAO.getCompanyById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), company);
    }

    @Override
    public Response<Company> createCompany(Request<ApplicationInputs> request) {
        Set<Branch> tempBranches = null;
        Set<RewardPolicy> tempPolicies = null;

        if(request.getBody().getCompany().getPolicies() != null){
            tempPolicies = request.getBody().getCompany().getPolicies();
            request.getBody().getCompany().setPolicies(null);
        }

        if(request.getBody().getCompany().getBranches() != null && request.getBody().getCompany().getBranches().size() > 0){
            tempBranches = request.getBody().getCompany().getBranches();
            request.getBody().getCompany().setBranches(null);
        }

        Company company = companyDAO.createCompany(request.getBody().getCompany());
        addToCompanyHistory("INSERT", company);

        if(tempBranches != null && tempBranches.size() > 0){
            tempBranches.forEach(branch -> {
                branch.setCompany(company);
                request.getBody().setBranch(branch);
                company.getBranches().add((branchService.createBranch(request)).getBody());

            });
        }

        if(tempPolicies != null && tempPolicies.size() > 0){
            tempPolicies.forEach(policy -> {
                policy.setCompany(company);
                request.getBody().setRewardPolicy(policy);
                company.getPolicies().add((rewardPolicyService.createRewardPolicy(request)).getBody());
            });
        }
        return new Response<>(new OutputHeader(), company);
    }

    @Override
    public Response<Company> updateCompany(Request<ApplicationInputs> request) {
        Company company = companyDAO.updateCompany(request.getBody().getCompany());
        addToCompanyHistory("UPDATE", company);
        return new Response<>(new OutputHeader(), company);
    }

    @Override
    public void deleteCompany(Request<ApplicationInputs> request) {
        Company company = companyDAO.getCompanyById(request.getBody().getEntityId());
        addToCompanyHistory("DELETE", company);
        companyDAO.deleteCompany(company);
    }

    /**
     * Adds a new row to the HST_COMPANY table for this company object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param company The {@link Company} object which has been changed
     */
    private void addToCompanyHistory(String changeDesc, Company company) {
        HstCompany hstCompany = new HstCompany(changeDesc, company);
        hstCompany = hstCompanyDao.createHstCompany(hstCompany);
        company.getHstCompanies().add(hstCompany);
    }
}
