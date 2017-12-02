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
public class CurrencyDaoImpl implements CurrencyDao{
    @Autowired
    public CurrencyDaoImpl(){
    }

    @Override
    public Currency getCurrencyById(int currencyId, Session session) {
        Currency currency = null;
        currency = session.get(Currency.class, currencyId);
        if(currency == null) throw new EmptyResultDataAccessException(1);

        return currency;
    }

    @Override
    public Currency updateCurrency(Currency currency, Session session) {
        session.update(currency);
        return getCurrencyById(currency.getId(), session);
    }

    @Override
    public Currency createCurrency(Currency currency, Session session) {
        int newEntityId = (int) session.save(currency);

        return getCurrencyById(newEntityId, session);
    }

    @Override
    public void deleteCurrency(Currency currency, Session session) {
        session.delete(currency);
    }
}
