package database.application.development.repository.impl;

import database.application.development.model.domain.Company;
import database.application.development.model.domain.RewardPolicy;
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
public class CompanyDaoImpl implements CompanyDao{

    @Autowired
    public CompanyDaoImpl(){
    }

    @Override
    public Company getCompanyById(int companyId, Session session) {
        Company company = null;
        company = session.get(Company.class, companyId);
        if(company == null) throw new EmptyResultDataAccessException(1);

        return company;
    }

    @Override
    public Company updateCompany(Company company, Session session) {
        session.update(company);
        return getCompanyById(company.getId(), session);
    }

    @Override
    public Company createCompany(Company company, Session session) {
        int newEntityId = (int) session.save(company);
        return getCompanyById(newEntityId, session);
    }

    @Override
    public void deleteCompany(Company company, Session session) {
        session.delete(company);
    }

    @Override
    public void deleteRewardPolicy(Company company, RewardPolicy rewardPolicy, Session session) {
        company.getPolicies().remove(rewardPolicy);
        session.update(rewardPolicy);
    }
}
