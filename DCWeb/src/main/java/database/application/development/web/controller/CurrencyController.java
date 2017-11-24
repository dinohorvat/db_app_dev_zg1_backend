package database.application.development.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.application.development.model.domain.Currency;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.InputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.util.Views;
import database.application.development.service.CurrencyService;
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
@RequestMapping("currency")
@Api(tags = "Currency", description = "Operations about currency")
@Slf4j
public class CurrencyController extends Serializer{

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService, ObjectMapper mapper) {
        super(mapper);
        this.currencyService = currencyService;
    }

    @GetMapping(name = "{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Currency by ID", notes = "Implementation for getting Currency by ID")
    public ResponseEntity<Response<Currency>> getCurrencyById(@PathVariable int id) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);

        Response<Currency> result = currencyService.getCurrencyById(new Request<>(header, inputs));
        ResponseEntity<Response<Currency>> response = new ResponseEntity<Response<Currency>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToCurrency());
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Currency", notes = "Implementation for creating a Currency")
    public ResponseEntity<Response<Currency>> createCurrency(@RequestBody Currency currency) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setCurrency(currency);
        Response<Currency> result = currencyService.createCurrency(new Request<>(header, inputs));
        ResponseEntity<Response<Currency>> response = new ResponseEntity<Response<Currency>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToCurrency());
    }

    @PutMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Currency", notes = "Implementation for updating a Currency")
    public ResponseEntity<Response<Currency>> updateCurrency(@RequestBody Currency currency) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setCurrency(currency);
        Response<Currency> result = currencyService.updateCurrency(new Request<>(header, inputs));
        ResponseEntity<Response<Currency>> response = new ResponseEntity<Response<Currency>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToCurrency());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Currency", notes = "Implementation for deleting a Currency")
    public ResponseEntity deleteCurrency(@PathVariable int id) {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);
        currencyService.deleteCurrency(new Request<>(header, inputs));

        return new ResponseEntity(HttpStatus.OK);
    }
}
