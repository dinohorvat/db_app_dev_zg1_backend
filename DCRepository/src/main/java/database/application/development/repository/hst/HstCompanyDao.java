package database.application.development.repository.hst;

import database.application.development.model.history.HstCompany;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstCompanyDao {
    HstCompany getHstCompanyById(int entityId, Session session);

    HstCompany updateHstCompany(HstCompany hstCompany);

    HstCompany createHstCompany(HstCompany hstCompany, Session session);

    void deleteHstCompany(HstCompany hstCompany);
}
