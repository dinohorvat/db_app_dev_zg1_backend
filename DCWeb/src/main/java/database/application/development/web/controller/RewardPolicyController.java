package database.application.development.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.InputHeader;
import database.application.development.model.messages.Request;
import database.application.development.service.LocationService;
import database.application.development.service.RewardPolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("reward_policy")
@Api(tags = "Reward Policy", description = "Operations about reward policies")
@Slf4j
public class RewardPolicyController extends Serializer{

    private RewardPolicyService rewardPolicyService;

    @Autowired
    public RewardPolicyController(RewardPolicyService rewardPolicyService, ObjectMapper mapper) {
        super(mapper);
        this.rewardPolicyService = rewardPolicyService;
    }

    @DeleteMapping("{rewardId}/{companyId}")
    @ApiOperation(value = "Delete Reward Policy", notes = "Implementation for deleting a Reward Policy")
    public ResponseEntity deleteRewardPolicy(@PathVariable("rewardId") int rewardId, @PathVariable("companyId") int companyId) {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(rewardId);
        inputs.setCompanyId(companyId);

        rewardPolicyService.deleteRewardPolicy(new Request<>(header, inputs));

        return new ResponseEntity(HttpStatus.OK);
    }
}
