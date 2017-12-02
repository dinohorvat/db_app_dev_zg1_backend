package database.application.development.repository.hst.impl;

import database.application.development.model.history.HstEmployee;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.repository.hst.HstEmployeeDao;
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
public class HstEmployeeDaoImpl extends ORMConfig implements HstEmployeeDao {
    @Autowired
    public HstEmployeeDaoImpl(){
        super();
    }

    @Override
    public HstEmployee getHstEmployeeById(int hstEmployeeId) {
        Session session = this.getSession();
        HstEmployee hstEmployee = null;
        Transaction transaction = session.beginTransaction();
        hstEmployee = session.get(HstEmployee.class, hstEmployeeId);
        if(hstEmployee == null) throw new EmptyResultDataAccessException(1);

        return hstEmployee;
    }

    @Override
    public HstEmployee updateHstEmployee(HstEmployee hstEmployee) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(hstEmployee);
        transaction.commit();
        session.close();

        return getHstEmployeeById(hstEmployee.getId());
    }

    @Override
    public HstEmployee createHstEmployee(HstEmployee hstEmployee) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(hstEmployee);
        transaction.commit();
        session.close();

        return getHstEmployeeById(newEntityId);
    }

    @Override
    public void deleteHstEmployee(HstEmployee hstEmployee) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(hstEmployee);
        transaction.commit();
        session.close();
    }
}

