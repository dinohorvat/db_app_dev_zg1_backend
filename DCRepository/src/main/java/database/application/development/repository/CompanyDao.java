package database.application.development.repository;

import database.application.development.model.domain.Company;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
public interface CompanyDao {

    Company getCompanyById(int entityId);

    Company updateCompany(Company company);

    Company createCompany(Company company);

    void deleteCompany(Company company);
}
