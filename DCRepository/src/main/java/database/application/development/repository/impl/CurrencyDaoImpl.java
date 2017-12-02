package database.application.development.repository.impl;

import database.application.development.model.domain.Currency;
import database.application.development.repository.CurrencyDao;
import database.application.development.repository.configuration.ORMConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CurrencyDaoImpl extends ORMConfig implements CurrencyDao{
    @Autowired
    public CurrencyDaoImpl(){
        super();
    }

    @Override
    public Currency getCurrencyById(int currencyId) {
        Session session = this.getSession();
        Currency currency = null;
        Transaction transaction = session.beginTransaction();
        currency = session.get(Currency.class, currencyId);
        if(currency == null) throw new EmptyResultDataAccessException(1);

        return currency;
    }

    @Override
    public Currency updateCurrency(Currency currency) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(currency);
        transaction.commit();
        session.close();

        return getCurrencyById(currency.getId());
    }

    @Override
    public Currency createCurrency(Currency currency) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(currency);
        transaction.commit();
        session.close();

        return getCurrencyById(newEntityId);
    }

    @Override
    public void deleteCurrency(Currency currency) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(currency);
        transaction.commit();
        session.close();
    }
}
