package database.application.development.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.application.development.model.domain.Company;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.InputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.util.Views;
import database.application.development.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@CrossOrigin
@RestController
@RequestMapping("company")
@Api(tags = "Company", description="Operations about Company")
@Slf4j
public class CompanyController extends Serializer{

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService, ObjectMapper mapper){
        super(mapper);
        this.companyService = companyService;
    }

    @GetMapping(path = "{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Company by ID", notes = "Implementation for getting Company by ID")
    public ResponseEntity<Response<Company>> getCompanyById(@PathVariable int id) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);

        Response<Company> result = companyService.getCompanyById(new Request<>(header, inputs));
        ResponseEntity<Response<Company>> response = new ResponseEntity<Response<Company>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToCompany());
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Company", notes = "Implementation for creating Company")
    public ResponseEntity<Response<Company>> createCompany(@RequestBody Company company) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setCompany(company);
        Response<Company> result = companyService.createCompany(new Request<>(header, inputs));
        ResponseEntity<Response<Company>> response = new ResponseEntity<Response<Company>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToCompany());
    }

    @PutMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Company", notes = "Implementation for updating Company")
    public ResponseEntity<Response<Company>> updateCompany(@RequestBody Company company) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setCompany(company);
        Response<Company> result = companyService.updateCompany(new Request<>(header, inputs));
        ResponseEntity<Response<Company>> response = new ResponseEntity<Response<Company>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToCompany());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Company", notes = "Implementation for deleting Company")
    public ResponseEntity deleteCompany(@PathVariable int id) {
        InputHeader header = new InputHeader();
        ApplicationInputs  inputs = new ApplicationInputs().setEntityId(id);
        companyService.deleteCompany(new Request<>(header, inputs));

        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        return response;
    }
}
