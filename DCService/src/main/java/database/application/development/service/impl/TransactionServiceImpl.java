package database.application.development.service.impl;

import database.application.development.model.domain.Transactions;
import database.application.development.model.history.HstTransaction;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.relation.RelCustomerProductTransaction;
import database.application.development.repository.TransactionsDao;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.repository.hst.HstTransactionDao;
import database.application.development.repository.relations.RelCustomerProductTransactionDao;
import database.application.development.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class TransactionServiceImpl extends ORMConfig implements TransactionService {

    private TransactionsDao transactionsDao;
    private HstTransactionDao hstTransactionDao;
    private RelCustomerProductTransactionDao relationDao;

    @Autowired
    public TransactionServiceImpl(TransactionsDao transactionsDao, HstTransactionDao hstTransactionDao, RelCustomerProductTransactionDao relationDao) {
        super();
        this.transactionsDao = transactionsDao;
        this.hstTransactionDao = hstTransactionDao;
        this.relationDao = relationDao;
    }

    @Override
    public Response<Transactions> getTransactionsById(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        Transactions transactions = this.transactionsDao.getTransactionsById(request.getBody().getEntityId(), session);
        return new Response<>(new OutputHeader(), transactions);
    }

    @Override
    public Response<Transactions> createTransactions(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();

        Set<RelCustomerProductTransaction> relations = request.getBody().getTransaction().getTransactionItems();
        request.getBody().getTransaction().setTransactionItems(null);

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        request.getBody().getTransaction().getDcsDate().setTransactionPlaced(timestamp);

        Transactions transactions = this.transactionsDao.createTransactions(request.getBody().getTransaction(), session);
        transactions.setTransactionItems(new HashSet<RelCustomerProductTransaction>());
        addToTransactionHistory("INSERT", transactions, session);

        relations.forEach((item) ->{
            item.setTransaction(transactions);
            item = relationDao.createRelCustomerProductTransaction(item, session);
            transactions.getTransactionItems().add(item);
        });

        session.getTransaction().commit();
        session.close();

        return new Response<>(new OutputHeader(), transactions);
    }

    @Override
    public Response<Transactions> updateTransactions(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();

        request.getBody().getTransaction().getTransactionItems().forEach((item) ->{
            item.setTransaction(request.getBody().getTransaction());
        });

        request.getBody().getTransaction().getHstTransactions().forEach(hstTransaction -> {
            hstTransaction.setTransaction(request.getBody().getTransaction());
        });

        Transactions transactions = this.transactionsDao.updateTransactions(request.getBody().getTransaction(), session);
        addToTransactionHistory("UPDATE", transactions, session);

        session.getTransaction().commit();
        session.close();

        return new Response<>(new OutputHeader(), transactions);
    }

    @Override
    public void deleteTransactions(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();

        Transactions transactions = transactionsDao.getTransactionsById(request.getBody().getEntityId(), session);
        addToTransactionHistory("DELETE - Cancel", transactions, session);
        transactionsDao.deleteTransactions(transactions, session);
        session.getTransaction().commit();
        session.close();
    }


    /**
     * Adds a new row to the HST_TRANSACTION table for this transactions object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param transactions The {@link Transactions} object which has been changed
     */
    private void addToTransactionHistory(String changeDesc, Transactions transactions, Session session) {
        HstTransaction hstTransaction = new HstTransaction(changeDesc, transactions);
        hstTransaction = hstTransactionDao.createHstTransaction(hstTransaction, session);
        if(transactions.getHstTransactions() == null) transactions.setHstTransactions(new HashSet<HstTransaction>());
        transactions.getHstTransactions().add(hstTransaction);
    }
}
