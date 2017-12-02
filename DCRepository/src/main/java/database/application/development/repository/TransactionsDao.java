package database.application.development.repository;

import database.application.development.model.domain.Transactions;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface TransactionsDao {
    Transactions getTransactionsById(int entityId, Session session);

    Transactions updateTransactions(Transactions transactions, Session session);

    Transactions createTransactions(Transactions transactions, Session session);

    void deleteTransactions(Transactions transactions, Session session);
}
