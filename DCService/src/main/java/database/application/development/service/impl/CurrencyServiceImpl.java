package database.application.development.service.impl;

import database.application.development.model.domain.Currency;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.CurrencyDao;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.service.CurrencyService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl extends ORMConfig implements CurrencyService {

    private CurrencyDao currencyDAO;

    @Autowired
    public CurrencyServiceImpl(CurrencyDao currencyDAO){
        super();
        this.currencyDAO = currencyDAO;
    }

    @Override
    public Response<Currency> getCurrencyById(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        Currency currency = currencyDAO.getCurrencyById(request.getBody().getEntityId(), session);
        session.close();
        return new Response<>(new OutputHeader(), currency);
    }

    @Override
    public Response<Currency> createCurrency(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Currency currency = currencyDAO.createCurrency(request.getBody().getCurrency(), session);
        session.getTransaction().commit();
        session.close();
        return new Response<>(new OutputHeader(), currency);
    }

    @Override
    public Response<Currency> updateCurrency(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Currency currency = currencyDAO.updateCurrency(request.getBody().getCurrency(), session);
        session.getTransaction().commit();
        session.close();
        return new Response<>(new OutputHeader(), currency);
    }

    @Override
    public void deleteCurrency(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Currency currency = currencyDAO.getCurrencyById(request.getBody().getEntityId(), session);
        currencyDAO.deleteCurrency(currency,session);
        session.getTransaction().commit();
        session.close();
    }
    
}
