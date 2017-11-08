package database.application.development.service;

import database.application.development.model.domain.Employee;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;

public interface EmployeeService {

    Response<Employee> getEmployeeById(Request<ApplicationInputs> request);

    Response<Employee> createEmployee(Request<ApplicationInputs> request);

    Response<Employee> updateEmployee(Request<ApplicationInputs> request);

    void deleteEmployee(Request<ApplicationInputs> request);
}
