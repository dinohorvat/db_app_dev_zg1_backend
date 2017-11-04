package database.application.development.repository.relations.impl;

import database.application.development.model.relation.RelCustomerProductTransaction;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.repository.relations.RelCustomerProductTransactionDao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RelCustomerProductTransactionDaoImpl extends ORMConfig implements RelCustomerProductTransactionDao{

    @Autowired
    public RelCustomerProductTransactionDaoImpl(){
        super();
    }

    @Override
    public RelCustomerProductTransaction getRelCustomerProductTransactionById(int entityId) {
        Session session = this.getSession();
        RelCustomerProductTransaction relation = null;
        Transaction transaction = session.beginTransaction();
        relation = session.get(RelCustomerProductTransaction.class, entityId);
        if(relation == null) throw new EmptyResultDataAccessException(1);
        transaction.commit();
        session.close();

        return relation;
    }

    @Override
    public RelCustomerProductTransaction updateRelCustomerProductTransaction(RelCustomerProductTransaction relation) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(relation);
        transaction.commit();
        session.close();

        return getRelCustomerProductTransactionById(relation.getId());
    }

    @Override
    public RelCustomerProductTransaction createRelCustomerProductTransaction(RelCustomerProductTransaction relation) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(relation);
        transaction.commit();
        session.close();

        return getRelCustomerProductTransactionById(newEntityId);
    }

    @Override
    public void deleteRelCustomerProductTransaction(RelCustomerProductTransaction relation) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(relation);
        transaction.commit();
        session.close();
    }
}
