package database.application.development.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.application.development.model.domain.Branch;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.InputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.util.Views;
import database.application.development.service.BranchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("branch")
@Api(tags = "Branch", description="Operations about Branch")
@Slf4j
public class BranchController extends Serializer{

    private BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService, ObjectMapper mapper) {
        super(mapper);
        this.branchService = branchService;
    }

    @GetMapping(path = "{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Branch by ID", notes = "Implementation for getting Branch by ID")
    public ResponseEntity<Response<Branch>> getBranchById(@PathVariable int id) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);

        Response<Branch> result = branchService.getBranchById(new Request<>(header, inputs));
        ResponseEntity<Response<Branch>> response = new ResponseEntity<Response<Branch>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToBranch());
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Branch", notes = "Implementation for creating a Branch")
    public ResponseEntity<Response<Branch>> createBranch(@RequestBody Branch branch) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setBranch(branch);
        Response<Branch> result = branchService.createBranch(new Request<>(header, inputs));
        ResponseEntity<Response<Branch>> response = new ResponseEntity<Response<Branch>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToBranch());
    }

    @PutMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Branch", notes = "Implementation for updating a Branch")
    public ResponseEntity<Response<Branch>> updateBranch(@RequestBody Branch branch) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setBranch(branch);
        Response<Branch> result = branchService.updateBranch(new Request<>(header, inputs));
        ResponseEntity<Response<Branch>> response = new ResponseEntity<Response<Branch>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToBranch());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Branch", notes = "Implementation for deleting a Branch")
    public ResponseEntity deleteBranch(@PathVariable int id) {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);
        branchService.deleteBranch(new Request<>(header, inputs));

        return new ResponseEntity(HttpStatus.OK);
    }
}
