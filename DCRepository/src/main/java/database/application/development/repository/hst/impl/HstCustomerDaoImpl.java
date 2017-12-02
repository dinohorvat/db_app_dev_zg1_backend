package database.application.development.repository.hst.impl;

import database.application.development.model.history.HstCustomer;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.repository.hst.HstCustomerDao;
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
public class HstCustomerDaoImpl extends ORMConfig implements HstCustomerDao {

    @Autowired
    public HstCustomerDaoImpl(){
        super();
    }

    @Override
    public HstCustomer getHstCustomerById(int hstCustomerId, Session session) {
        HstCustomer hstCustomer = null;
        hstCustomer = session.get(HstCustomer.class, hstCustomerId);
        if(hstCustomer == null) throw new EmptyResultDataAccessException(1);

        return hstCustomer;
    }

    @Override
    public HstCustomer updateHstCustomer(HstCustomer hstCustomer) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(hstCustomer);
        transaction.commit();
        session.close();

        return getHstCustomerById(hstCustomer.getId(), session);
    }

    @Override
    public HstCustomer createHstCustomer(HstCustomer hstCustomer, Session session) {
        int newEntityId = (int) session.save(hstCustomer);

        return getHstCustomerById(newEntityId, session);
    }

    @Override
    public void deleteHstCustomer(HstCustomer hstCustomer) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(hstCustomer);
        transaction.commit();
        session.close();
    }
}
