package database.application.development.repository.hst;

import database.application.development.model.history.HstCompany;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstCompanyDao {
    HstCompany getHstCompanyById(int entityId);

    HstCompany updateHstCompany(HstCompany hstCompany);

    HstCompany createHstCompany(HstCompany hstCompany);

    void deleteHstCompany(HstCompany hstCompany);
}
