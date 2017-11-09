package database.application.development.service;

import database.application.development.model.domain.RewardPolicy;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;

public interface RewardPolicyService {

    Response<RewardPolicy> getRewardPolicyById(Request<ApplicationInputs> request);

    Response<RewardPolicy> createRewardPolicy(Request<ApplicationInputs> request);

    Response<RewardPolicy> updateRewardPolicy(Request<ApplicationInputs> request);

    void deleteRewardPolicy(Request<ApplicationInputs> request);
}
