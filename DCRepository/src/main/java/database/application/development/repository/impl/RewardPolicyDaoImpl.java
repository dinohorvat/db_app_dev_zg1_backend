package database.application.development.repository.impl;

import database.application.development.model.domain.RewardPolicy;
import database.application.development.repository.RewardPolicyDao;
import database.application.development.repository.configuration.ORMConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RewardPolicyDaoImpl extends ORMConfig implements RewardPolicyDao{
    @Autowired
    public RewardPolicyDaoImpl(){
        super();
    }

    @Override
    public RewardPolicy getRewardPolicyById(int rewardPolicyId) {
        Session session = this.getSession();
        RewardPolicy rewardPolicy = null;
        Transaction transaction = session.beginTransaction();
        rewardPolicy = session.get(RewardPolicy.class, rewardPolicyId);
        if(rewardPolicy == null) throw new EmptyResultDataAccessException(1);

        return rewardPolicy;
    }

    @Override
    public RewardPolicy updateRewardPolicy(RewardPolicy rewardPolicy) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(rewardPolicy);
        transaction.commit();
        session.close();

        return getRewardPolicyById(rewardPolicy.getId());
    }

    @Override
    public RewardPolicy createRewardPolicy(RewardPolicy rewardPolicy) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(rewardPolicy);
        transaction.commit();
        session.close();

        return getRewardPolicyById(newEntityId);
    }

    @Override
    public void deleteRewardPolicy(RewardPolicy rewardPolicy) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(rewardPolicy);
        transaction.commit();
        session.close();
    }
}
