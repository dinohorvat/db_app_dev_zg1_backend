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
public class EmployeeDaoImpl extends ORMConfig implements EmployeeDao {
    @Autowired
    public EmployeeDaoImpl(){
        super();
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Session session = this.getSession();
        Employee employee = null;
        Transaction transaction = session.beginTransaction();
        employee = session.get(Employee.class, employeeId);
        if(employee == null) throw new EmptyResultDataAccessException(1);
        transaction.commit();
        session.close();

        return employee;
    }

    @Override
    public Employee findByEmail(Employee employee) {
        Session session = this.getSession();

        String hql = "FROM employee WHERE username = :username";
        Employee result = (Employee) session.createQuery(hql)
                .setString("username", employee.getUsername())
                .uniqueResult();

        if(result == null){
            session.close();
            throw new EmptyResultDataAccessException(1);
        }

        session.close();
        return result;
    }


    @Override
    public Employee updateEmployee(Employee employee) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(employee);
        transaction.commit();
        session.close();

        return getEmployeeById(employee.getId());
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(employee);
        transaction.commit();
        session.close();

        return getEmployeeById(newEntityId);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(employee);
        transaction.commit();
        session.close();
    }
}

