package database.application.development.repository;

import database.application.development.model.domain.Currency;
import org.hibernate.Session;

public interface CurrencyDao {

    Currency getCurrencyById(int entityId, Session session);

    Currency updateCurrency(Currency currency, Session session);

    Currency createCurrency(Currency currency, Session session);

    void deleteCurrency(Currency currency, Session session);
}
