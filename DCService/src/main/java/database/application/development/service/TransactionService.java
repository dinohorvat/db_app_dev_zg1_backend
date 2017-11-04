package database.application.development.service;

import database.application.development.model.domain.Transactions;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;

public interface TransactionService {

    Response<Transactions> getTransactionsById(Request<ApplicationInputs> request);

    Response<Transactions> createTransactions(Request<ApplicationInputs> request);

    Response<Transactions> updateTransactions(Request<ApplicationInputs> request);

    void deleteTransactions(Request<ApplicationInputs> request);
}
