package database.application.development.repository;

import database.application.development.model.domain.Employee;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface EmployeeDao {
    Employee getEmployeeById(int entityId);

    Employee findByEmail(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee createEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
