package database.application.development.repository.hst;

import database.application.development.model.history.HstProduct;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstProductDao {
    HstProduct getHstProductById(int entityId);

    HstProduct updateHstProduct(HstProduct hstProduct);

    HstProduct createHstProduct(HstProduct hstProduct);

    void deleteHstProduct(HstProduct hstProduct);
}
