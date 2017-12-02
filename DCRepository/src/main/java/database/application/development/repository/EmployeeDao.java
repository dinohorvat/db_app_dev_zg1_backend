package database.application.development.repository;

import database.application.development.model.domain.Employee;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface EmployeeDao {
    Employee getEmployeeById(int entityId, Session session);

    Employee findByEmail(Employee employee, Session session);

    Employee updateEmployee(Employee employee, Session session);

    Employee createEmployee(Employee employee, Session session);

    void deleteEmployee(Employee employee, Session session);
}
