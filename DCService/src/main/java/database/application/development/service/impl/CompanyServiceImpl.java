package database.application.development.service.impl;

import database.application.development.model.domain.Company;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.CompanyDao;
import database.application.development.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService{

    private CompanyDao companyDAO;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDAO){
        this.companyDAO = companyDAO;
    }

    @Override
    public Response<Company> getCompanyById(Request<ApplicationInputs> request) {
        Company company = companyDAO.getCompanyById(request.getBody().getEntityId());

        //TODO: insert hst

        return new Response<>(new OutputHeader(), company);
    }

    @Override
    public Response<Company> createCompany(Request<ApplicationInputs> request) {
        Company company = companyDAO.createCompany(request.getBody().getCompany());

        //TODO: insert hst

        return new Response<>(new OutputHeader(), company);
    }

    @Override
    public Response<Company> updateCompany(Request<ApplicationInputs> request) {
        Company company = companyDAO.updateCompany(request.getBody().getCompany());

        //TODO: insert hst

        return new Response<>(new OutputHeader(), company);
    }

    @Override
    public void deleteCompany(Request<ApplicationInputs> request) {
        Company company = companyDAO.getCompanyById(request.getBody().getEntityId());

        //TODO: insert hst

        companyDAO.deleteCompany(company);
    }
}
