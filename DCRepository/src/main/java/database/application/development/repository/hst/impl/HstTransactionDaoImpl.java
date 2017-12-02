package database.application.development.repository.hst.impl;

import database.application.development.model.history.HstTransaction;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.repository.hst.HstTransactionDao;
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
public class HstTransactionDaoImpl extends ORMConfig implements HstTransactionDao {
    @Autowired
    public HstTransactionDaoImpl(){
        super();
    }

    @Override
    public HstTransaction getHstTransactionById(int hstTransactionId, Session session) {
        HstTransaction hstTransaction = null;
        hstTransaction = session.get(HstTransaction.class, hstTransactionId);
        if(hstTransaction == null) throw new EmptyResultDataAccessException(1);

        return hstTransaction;
    }

    @Override
    public HstTransaction updateHstTransaction(HstTransaction hstTransaction) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(hstTransaction);
        transaction.commit();
        session.close();

        return getHstTransactionById(hstTransaction.getId(), session);
    }

    @Override
    public HstTransaction createHstTransaction(HstTransaction hstTransaction, Session session) {
        int newEntityId = (int) session.save(hstTransaction);

        return getHstTransactionById(newEntityId, session);
    }

    @Override
    public void deleteHstTransaction(HstTransaction hstTransaction) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(hstTransaction);
        transaction.commit();
        session.close();
    }
}
