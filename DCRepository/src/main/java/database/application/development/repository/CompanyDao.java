package database.application.development.repository;

import database.application.development.model.domain.Company;
import database.application.development.model.domain.RewardPolicy;
import org.hibernate.Session;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
public interface CompanyDao {

    Company getCompanyById(int entityId, Session session);

    Company updateCompany(Company company, Session session);

    Company createCompany(Company company, Session session);

    void deleteCompany(Company company, Session session);

    void deleteRewardPolicy(Company company, RewardPolicy rewardPolicy, Session session);
}
