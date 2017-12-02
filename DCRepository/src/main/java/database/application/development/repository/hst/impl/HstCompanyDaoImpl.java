package database.application.development.repository.hst.impl;

import database.application.development.model.history.HstCompany;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.repository.hst.HstCompanyDao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Created by dinohorvat on 30/10/2017.
 */
@Slf4j
@Repository
public class HstCompanyDaoImpl extends ORMConfig implements HstCompanyDao {
    @Autowired
    public HstCompanyDaoImpl(){
        super();
    }

    @Override
    public HstCompany getHstCompanyById(int hstCompanyId, Session session) {
        HstCompany hstCompany = null;
        hstCompany = session.get(HstCompany.class, hstCompanyId);
        if(hstCompany == null) throw new EmptyResultDataAccessException(1);

        return hstCompany;
    }

    @Override
    //Adjustment needed
    public HstCompany updateHstCompany(HstCompany hstCompany) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(hstCompany);
        transaction.commit();
        session.close();

        return getHstCompanyById(hstCompany.getId(), session);
    }

    @Override
    public HstCompany createHstCompany(HstCompany hstCompany, Session session) {
        int newEntityId = (int) session.save(hstCompany);

        return getHstCompanyById(newEntityId, session);
    }

    @Override
    public void deleteHstCompany(HstCompany hstCompany) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(hstCompany);
        transaction.commit();
        session.close();
    }
}
