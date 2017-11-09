package database.application.development.repository;

import database.application.development.model.domain.RewardPolicy;

public interface RewardPolicyDao {

    RewardPolicy getRewardPolicyById(int entityId);

    RewardPolicy updateRewardPolicy(RewardPolicy rewardPolicy);

    RewardPolicy createRewardPolicy(RewardPolicy rewardPolicy);

    void deleteRewardPolicy(RewardPolicy rewardPolicy);
}
