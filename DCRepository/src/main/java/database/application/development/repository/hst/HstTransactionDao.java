package database.application.development.repository.hst;

import database.application.development.model.history.HstTransaction;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstTransactionDao {
    HstTransaction getHstTransactionById(int entityId);

    HstTransaction updateHstTransaction(HstTransaction hstTransaction);

    HstTransaction createHstTransaction(HstTransaction hstTransaction);

    void deleteHstTransaction(HstTransaction hstTransaction);
}
