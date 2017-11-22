package database.application.development.service.impl;

import database.application.development.model.domain.Employee;
import database.application.development.model.history.HstEmployee;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.EmployeeDao;
import database.application.development.repository.hst.HstEmployeeDao;
import database.application.development.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
    private HstEmployeeDao hstEmployeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, HstEmployeeDao hstEmployeeDao) {
        this.employeeDao = employeeDao;
        this.hstEmployeeDao = hstEmployeeDao;
    }

    @Override
    public Response<Employee> getEmployeeById(Request<ApplicationInputs> request) {
        Employee employee = employeeDao.getEmployeeById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), employee);
    }

    @Override
    public Response<Employee> createEmployee(Request<ApplicationInputs> request) {
        Employee employee = employeeDao.createEmployee(request.getBody().getEmployee());
        addToEmployeeHistory("INSERT", employee);
        return new Response<>(new OutputHeader(), employee);
    }

    @Override
    public Response<Employee> updateEmployee(Request<ApplicationInputs> request) {
        Employee employee = employeeDao.updateEmployee(request.getBody().getEmployee());
        addToEmployeeHistory("UPDATE", employee);
        return new Response<>(new OutputHeader(), employee);
    }

    @Override
    public Response<Employee> findByUsername(Request<ApplicationInputs> request) {
        Employee employee = employeeDao.findByEmail(request.getBody().getEmployee());
        return new Response<>(new OutputHeader(), employee);
    }

    @Override
    public void deleteEmployee(Request<ApplicationInputs> request) {
        Employee employee = employeeDao.getEmployeeById(request.getBody().getEntityId());
        addToEmployeeHistory("DELETE", employee);
        employeeDao.deleteEmployee(employee);
    }

    /**
     * Adds a new row to the HST_EMPLOYEE table for this employee object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param employee The {@link Employee} object which has been changed
     */
    private void addToEmployeeHistory(String changeDesc, Employee employee) {
        HstEmployee hstEmployee = new HstEmployee(changeDesc, employee);
        hstEmployee = hstEmployeeDao.createHstEmployee(hstEmployee);
        employee.getHstEmployees().add(hstEmployee);
    }
}
