package database.application.development.service.impl;

import database.application.development.model.domain.Transactions;
import database.application.development.model.history.HstTransaction;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.relation.RelCustomerProductTransaction;
import database.application.development.repository.TransactionsDao;
import database.application.development.repository.hst.HstTransactionDao;
import database.application.development.repository.relations.RelCustomerProductTransactionDao;
import database.application.development.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private TransactionsDao transactionsDao;
    private HstTransactionDao hstTransactionDao;
    private RelCustomerProductTransactionDao relationDao;

    @Autowired
    public TransactionServiceImpl(TransactionsDao transactionsDao, HstTransactionDao hstTransactionDao, RelCustomerProductTransactionDao relationDao) {
        this.transactionsDao = transactionsDao;
        this.hstTransactionDao = hstTransactionDao;
        this.relationDao = relationDao;
    }

    @Override
    public Response<Transactions> getTransactionsById(Request<ApplicationInputs> request) {
        Transactions transactions = this.transactionsDao.getTransactionsById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), transactions);
    }

    @Override
    public Response<Transactions> createTransactions(Request<ApplicationInputs> request) {
        Set<RelCustomerProductTransaction> relations = request.getBody().getTransaction().getTransactionItems();
        request.getBody().getTransaction().setTransactionItems(null);

        Transactions transactions = this.transactionsDao.createTransactions(request.getBody().getTransaction());
        addToTransactionHistory("INSERT", transactions);

        relations.forEach((item) ->{
            item.setTransaction(transactions);
            item = relationDao.createRelCustomerProductTransaction(item);
            transactions.getTransactionItems().add(item);
        });

        return new Response<>(new OutputHeader(), transactions);
    }

    @Override
    public Response<Transactions> updateTransactions(Request<ApplicationInputs> request) {

        request.getBody().getTransaction().getTransactionItems().forEach((item) ->{
            item.setTransaction(request.getBody().getTransaction());
        });

        Transactions transactions = this.transactionsDao.updateTransactions(request.getBody().getTransaction());
        addToTransactionHistory("UPDATE", transactions);

        return new Response<>(new OutputHeader(), transactions);
    }

    @Override
    public void deleteTransactions(Request<ApplicationInputs> request) {
        Transactions transactions = transactionsDao.getTransactionsById(request.getBody().getEntityId());
        addToTransactionHistory("DELETE - Cancel", transactions);
        transactionsDao.deleteTransactions(transactions);
    }


    /**
     * Adds a new row to the HST_TRANSACTION table for this product object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param transactions The {@link Transactions} object which has been changed
     */
    private void addToTransactionHistory(String changeDesc, Transactions transactions) {
        HstTransaction hstTransaction = new HstTransaction(changeDesc, transactions);
        hstTransaction = hstTransactionDao.createHstTransaction(hstTransaction);
        transactions.getHstTransactions().add(hstTransaction);
    }
}
