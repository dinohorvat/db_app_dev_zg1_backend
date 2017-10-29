package database.application.development.repository;

import database.application.development.model.domain.Transactions;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface TransactionsDao {
    Transactions getTransactionsById(int entityId);

    Transactions updateTransactions(Transactions transactions);

    Transactions createTransactions(Transactions transactions);

    void deleteTransactions(Transactions transactions);
}
