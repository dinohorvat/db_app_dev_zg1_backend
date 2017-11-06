package database.application.development.service.impl;

import database.application.development.model.domain.Company;
import database.application.development.model.history.HstCompany;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.CompanyDao;
import database.application.development.repository.hst.HstCompanyDao;
import database.application.development.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDAO;
    private HstCompanyDao hstCompanyDao;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDAO, HstCompanyDao hstCompanyDao){
        this.companyDAO = companyDAO;
        this.hstCompanyDao = hstCompanyDao;
    }

    @Override
    public Response<Company> getCompanyById(Request<ApplicationInputs> request) {
        Company company = companyDAO.getCompanyById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), company);
    }

    @Override
    public Response<Company> createCompany(Request<ApplicationInputs> request) {
        Company company = companyDAO.createCompany(request.getBody().getCompany());

        addToCompanyHistory("INSERT", company);

        return new Response<>(new OutputHeader(), company);
    }

    @Override
    public Response<Company> updateCompany(Request<ApplicationInputs> request) {
        Company company = companyDAO.updateCompany(request.getBody().getCompany());

        addToCompanyHistory("UPDATE", company);

        return new Response<>(new OutputHeader(), company);
    }

    @Override
    public void deleteCompany(Request<ApplicationInputs> request) {
        Company company = companyDAO.getCompanyById(request.getBody().getEntityId());

        addToCompanyHistory("DELETE", company);

        companyDAO.deleteCompany(company);
    }

    /**
     * Adds a new row to the HST_COMPANY table for this product object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param company The {@link Company} object which has been changed
     */
    private void addToCompanyHistory(String changeDesc, Company company) {
        HstCompany hstCompany = new HstCompany(changeDesc, company);
        hstCompany = hstCompanyDao.createHstCompany(hstCompany);
        company.getHstCompanies().add(hstCompany);
    }
}
