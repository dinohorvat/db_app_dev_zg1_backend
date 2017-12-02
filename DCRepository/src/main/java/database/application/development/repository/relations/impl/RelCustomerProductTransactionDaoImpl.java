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
public class RelCustomerProductTransactionDaoImpl implements RelCustomerProductTransactionDao{

    @Autowired
    public RelCustomerProductTransactionDaoImpl(){
        super();
    }

    @Override
    public RelCustomerProductTransaction getRelCustomerProductTransactionById(int entityId, Session session) {
        RelCustomerProductTransaction relation = null;
        relation = session.get(RelCustomerProductTransaction.class, entityId);
        if(relation == null) throw new EmptyResultDataAccessException(1);

        return relation;
    }

    @Override
    public RelCustomerProductTransaction updateRelCustomerProductTransaction(RelCustomerProductTransaction relation, Session session) {
        session.update(relation);
        return getRelCustomerProductTransactionById(relation.getId(), session);
    }

    @Override
    public RelCustomerProductTransaction createRelCustomerProductTransaction(RelCustomerProductTransaction relation, Session session) {
        int newEntityId = (int) session.save(relation);
        return getRelCustomerProductTransactionById(newEntityId, session);
    }

    @Override
    public void deleteRelCustomerProductTransaction(RelCustomerProductTransaction relation, Session session) {
        session.delete(relation);
    }
}
