package database.application.development.repository.impl;

import database.application.development.model.domain.Transactions;
import database.application.development.model.util.TransactionStatus;
import database.application.development.repository.TransactionsDao;
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
public class TransactionsDaoImpl implements TransactionsDao {

    @Autowired
    public TransactionsDaoImpl(){
        super();
    }

    @Override
    public Transactions getTransactionsById(int transactionsId, Session session) {
        Transactions transactions = null;
        transactions = session.get(Transactions.class, transactionsId);
        if(transactions == null) throw new EmptyResultDataAccessException(1);

        return transactions;
    }

    @Override
    public Transactions updateTransactions(Transactions transactions, Session session) {

        session.update(transactions);

        return getTransactionsById(transactions.getId(), session);
    }

    @Override
    public Transactions createTransactions(Transactions transactions, Session session) {

        int newEntityId = (int) session.save(transactions);

        return getTransactionsById(newEntityId, session);
    }

    @Override
    public void deleteTransactions(Transactions transactions, Session session) {
        transactions.setStatus(TransactionStatus.CANCELED);
        session.update(transactions);
    }
}
