package database.application.development.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.application.development.model.domain.Transactions;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.InputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.util.Views;
import database.application.development.service.TransactionService;
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
@RequestMapping("transactions")
@Api(tags = "Transactions", description = "Operations about transactions")
@Slf4j
public class TransactionsController extends Serializer{

    private TransactionService transactionsService;

    @Autowired
    public TransactionsController(ObjectMapper mapper, TransactionService transactionsService) {
        super(mapper);
        this.transactionsService = transactionsService;
    }

    @GetMapping(path = "{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Transactions by ID", notes = "Implementation for getting a Transactions by ID")
    public ResponseEntity<Response<Transactions>> getTransactionsById(@PathVariable int id) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);

        Response<Transactions> result = transactionsService.getTransactionsById(new Request<>(header, inputs));
        ResponseEntity<Response<Transactions>> response = new ResponseEntity<Response<Transactions>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToTransaction());
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Transactions", notes = "Implementation for creating a Transactions")
    public ResponseEntity<Response<Transactions>> createTransactions(@RequestBody Transactions transactions) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setTransaction(transactions);
        Response<Transactions> result = transactionsService.createTransactions(new Request<>(header, inputs));
        ResponseEntity<Response<Transactions>> response = new ResponseEntity<Response<Transactions>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToTransaction());
    }

    @PutMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Transactions", notes = "Implementation for updating a Transactions")
    public ResponseEntity<Response<Transactions>> updateTransactions(@RequestBody Transactions transactions) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setTransaction(transactions);
        Response<Transactions> result = transactionsService.updateTransactions(new Request<>(header, inputs));
        ResponseEntity<Response<Transactions>> response = new ResponseEntity<Response<Transactions>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToTransaction());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Transactions", notes = "Implementation for deleting a Transactions")
    public ResponseEntity deleteTransactions(@PathVariable int id) {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);
        transactionsService.deleteTransactions(new Request<>(header, inputs));

        return new ResponseEntity(HttpStatus.OK);
    }
}
