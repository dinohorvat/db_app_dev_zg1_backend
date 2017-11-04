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
public class TransactionsDaoImpl extends ORMConfig implements TransactionsDao {

    @Autowired
    public TransactionsDaoImpl(){
        super();
    }

    @Override
    public Transactions getTransactionsById(int transactionsId) {
        Session session = this.getSession();
        Transactions transactions = null;
        Transaction transaction = session.beginTransaction();
        transactions = session.get(Transactions.class, transactionsId);
        if(transactions == null) throw new EmptyResultDataAccessException(1);
        transaction.commit();
        session.close();

        return transactions;
    }

    @Override
    public Transactions updateTransactions(Transactions transactions) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(transactions);
        transaction.commit();
        session.close();

        return getTransactionsById(transactions.getId());
    }

    @Override
    public Transactions createTransactions(Transactions transactions) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(transactions);
        transaction.commit();
        session.close();

        return getTransactionsById(newEntityId);
    }

    @Override
    public void deleteTransactions(Transactions transactions) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        transactions.setStatus(TransactionStatus.CANCELED);
        session.update(transactions);
        transaction.commit();
        session.close();
    }
}
