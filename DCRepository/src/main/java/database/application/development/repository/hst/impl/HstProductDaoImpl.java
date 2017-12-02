package database.application.development.repository.hst.impl;

import database.application.development.model.history.HstProduct;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.repository.hst.HstProductDao;
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
public class HstProductDaoImpl extends ORMConfig implements HstProductDao {
    @Autowired
    public HstProductDaoImpl(){
        super();
    }

    @Override
    public HstProduct getHstProductById(int hstProductId, Session session) {
        HstProduct hstProduct = null;
        hstProduct = session.get(HstProduct.class, hstProductId);
        if(hstProduct == null) throw new EmptyResultDataAccessException(1);

        return hstProduct;
    }

    @Override
    public HstProduct updateHstProduct(HstProduct hstProduct) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(hstProduct);
        transaction.commit();
        session.close();

        return getHstProductById(hstProduct.getId(), session);
    }

    @Override
    public HstProduct createHstProduct(HstProduct hstProduct, Session session) {
        int newEntityId = (int) session.save(hstProduct);

        return getHstProductById(newEntityId, session);
    }

    @Override
    public void deleteHstProduct(HstProduct hstProduct) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(hstProduct);
        transaction.commit();
        session.close();
    }
}
