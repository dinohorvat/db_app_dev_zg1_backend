package database.application.development.repository.impl;

import database.application.development.model.domain.Company;
import database.application.development.repository.CompanyDao;
import database.application.development.repository.configuration.ORMConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Slf4j
@Repository
public class CompanyDaoImpl extends ORMConfig implements CompanyDao{

    @Autowired
    public CompanyDaoImpl(){
        super();
    }

    @Override
    public Company getCompanyById(int companyId) {
        Session session = this.getSession();
        Company company = null;
        Transaction transaction = session.beginTransaction();
        company = session.get(Company.class, companyId);
        if(company == null) throw new EmptyResultDataAccessException(1);

        return company;
    }

    @Override
    public Company updateCompany(Company company) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(company);
        transaction.commit();
        session.close();

        return getCompanyById(company.getId());
    }

    @Override
    public Company createCompany(Company company) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(company);
        transaction.commit();
        session.close();

        return getCompanyById(newEntityId);
    }

    @Override
    public void deleteCompany(Company company) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(company);
        transaction.commit();
        session.close();
    }
}
