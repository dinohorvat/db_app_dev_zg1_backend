package database.application.development.service.impl;

import database.application.development.model.domain.RewardPolicy;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.RewardPolicyDao;
import database.application.development.service.RewardPolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RewardPolicyServiceImpl implements RewardPolicyService{

    private RewardPolicyDao rewardPolicyDao;

    @Autowired
    public RewardPolicyServiceImpl(RewardPolicyDao rewardPolicyDao) {
        this.rewardPolicyDao = rewardPolicyDao;
    }

    @Override
    public Response<RewardPolicy> getRewardPolicyById(Request<ApplicationInputs> request) {
        RewardPolicy rewardPolicy = rewardPolicyDao.getRewardPolicyById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), rewardPolicy);
    }

    @Override
    public Response<RewardPolicy> createRewardPolicy(Request<ApplicationInputs> request) {
        RewardPolicy rewardPolicy = rewardPolicyDao.createRewardPolicy(request.getBody().getRewardPolicy());

        return new Response<>(new OutputHeader(), rewardPolicy);
    }

    @Override
    public Response<RewardPolicy> updateRewardPolicy(Request<ApplicationInputs> request) {
        RewardPolicy rewardPolicy = rewardPolicyDao.updateRewardPolicy(request.getBody().getRewardPolicy());

        return new Response<>(new OutputHeader(), rewardPolicy);
    }

    @Override
    public void deleteRewardPolicy(Request<ApplicationInputs> request) {
        RewardPolicy rewardPolicy = rewardPolicyDao.getRewardPolicyById(request.getBody().getEntityId());

        rewardPolicyDao.deleteRewardPolicy(rewardPolicy);
    }

}
