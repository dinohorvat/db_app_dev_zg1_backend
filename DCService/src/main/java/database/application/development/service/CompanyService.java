package database.application.development.service;

import database.application.development.model.domain.Company;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
public interface CompanyService {

    Response<Company> getCompanyById(Request<ApplicationInputs> request);

    Response<Company> createCompany(Request<ApplicationInputs> request);

    Response<Company> updateCompany(Request<ApplicationInputs> request);

    void deleteCompany(Request<ApplicationInputs> request);
}
