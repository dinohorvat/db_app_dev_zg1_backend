package database.application.development.repository.hst;

import database.application.development.model.history.HstCustomer;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstCustomerDao {
    HstCustomer getHstCustomerById(int entityId);

    HstCustomer updateHstCustomer(HstCustomer hstCustomer);

    HstCustomer createHstCustomer(HstCustomer hstCustomer);

    void deleteHstCustomer(HstCustomer hstCustomer);
}
