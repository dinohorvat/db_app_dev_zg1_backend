package database.application.development.repository.hst;

import database.application.development.model.history.HstProduct;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstProductDao {
    HstProduct getHstProductById(int entityId, Session session);

    HstProduct updateHstProduct(HstProduct hstProduct);

    HstProduct createHstProduct(HstProduct hstProduct, Session session);

    void deleteHstProduct(HstProduct hstProduct);
}
