package database.application.development.service;

import database.application.development.model.domain.Currency;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;

public interface CurrencyService {

    Response<Currency> getCurrencyById(Request<ApplicationInputs> request);

    Response<Currency> createCurrency(Request<ApplicationInputs> request);

    Response<Currency> updateCurrency(Request<ApplicationInputs> request);

    void deleteCurrency(Request<ApplicationInputs> request);
}
