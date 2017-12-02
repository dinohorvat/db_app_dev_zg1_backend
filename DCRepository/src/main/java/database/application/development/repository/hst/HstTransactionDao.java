package database.application.development.repository.hst;

import database.application.development.model.history.HstTransaction;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstTransactionDao {
    HstTransaction getHstTransactionById(int entityId, Session session);

    HstTransaction updateHstTransaction(HstTransaction hstTransaction);

    HstTransaction createHstTransaction(HstTransaction hstTransaction, Session session);

    void deleteHstTransaction(HstTransaction hstTransaction);
}
