package database.application.development.repository.hst;

import database.application.development.model.history.HstCustomer;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstCustomerDao {
    HstCustomer getHstCustomerById(int entityId, Session session);

    HstCustomer updateHstCustomer(HstCustomer hstCustomer);

    HstCustomer createHstCustomer(HstCustomer hstCustomer, Session session);

    void deleteHstCustomer(HstCustomer hstCustomer);
}
