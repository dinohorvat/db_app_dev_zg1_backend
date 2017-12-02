package database.application.development.repository;

import database.application.development.model.domain.RewardPolicy;
import org.hibernate.Session;

public interface RewardPolicyDao {

    RewardPolicy getRewardPolicyById(int entityId, Session session);

    RewardPolicy updateRewardPolicy(RewardPolicy rewardPolicy, Session session);

    RewardPolicy createRewardPolicy(RewardPolicy rewardPolicy, Session session);

    void deleteRewardPolicy(RewardPolicy rewardPolicy, Session session);
}
