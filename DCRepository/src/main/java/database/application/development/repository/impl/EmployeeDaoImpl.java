package database.application.development.repository.impl;

import database.application.development.model.domain.Employee;
import database.application.development.repository.EmployeeDao;
import database.application.development.repository.configuration.ORMConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Created by dinohorvat on 30/10/2017.
 */
@Slf4j
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    public EmployeeDaoImpl(){
        super();
    }

    @Override
    public Employee getEmployeeById(int employeeId, Session session) {
        Employee employee = null;
        employee = session.get(Employee.class, employeeId);
        if(employee == null) throw new EmptyResultDataAccessException(1);

        return employee;
    }

    @Override
    public Employee findByEmail(Employee employee, Session session) {
        String hql = "FROM employee WHERE username = :username";
        Employee result = (Employee) session.createQuery(hql)
                .setString("username", employee.getUsername())
                .uniqueResult();

        if(result == null){
            throw new EmptyResultDataAccessException(1);
        }

        return result;
    }


    @Override
    public Employee updateEmployee(Employee employee, Session session) {
        session.update(employee);
        return getEmployeeById(employee.getId(), session);
    }

    @Override
    public Employee createEmployee(Employee employee, Session session) {
        int newEntityId = (int) session.save(employee);
        return getEmployeeById(newEntityId, session);
    }

    @Override
    public void deleteEmployee(Employee employee, Session session) {
        session.delete(employee);
    }
}

