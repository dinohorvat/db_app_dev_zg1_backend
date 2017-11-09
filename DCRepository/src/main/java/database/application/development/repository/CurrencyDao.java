package database.application.development.repository;

import database.application.development.model.domain.Currency;

public interface CurrencyDao {

    Currency getCurrencyById(int entityId);

    Currency updateCurrency(Currency currency);

    Currency createCurrency(Currency currency);

    void deleteCurrency(Currency currency);
}
