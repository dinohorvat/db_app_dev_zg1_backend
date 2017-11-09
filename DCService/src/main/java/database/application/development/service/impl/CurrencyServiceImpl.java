package database.application.development.service.impl;

import database.application.development.model.domain.Currency;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.CurrencyDao;
import database.application.development.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyDao currencyDAO;

    @Autowired
    public CurrencyServiceImpl(CurrencyDao currencyDAO){
        this.currencyDAO = currencyDAO;
    }

    @Override
    public Response<Currency> getCurrencyById(Request<ApplicationInputs> request) {
        Currency currency = currencyDAO.getCurrencyById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), currency);
    }

    @Override
    public Response<Currency> createCurrency(Request<ApplicationInputs> request) {
        Currency currency = currencyDAO.createCurrency(request.getBody().getCurrency());
        return new Response<>(new OutputHeader(), currency);
    }

    @Override
    public Response<Currency> updateCurrency(Request<ApplicationInputs> request) {
        Currency currency = currencyDAO.updateCurrency(request.getBody().getCurrency());
        return new Response<>(new OutputHeader(), currency);
    }

    @Override
    public void deleteCurrency(Request<ApplicationInputs> request) {
        Currency currency = currencyDAO.getCurrencyById(request.getBody().getEntityId());
        currencyDAO.deleteCurrency(currency);
    }
    
}
