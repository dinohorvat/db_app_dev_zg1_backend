package database.application.development.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.application.development.model.domain.Customer;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.InputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.util.Email;
import database.application.development.model.util.Views;
import database.application.development.service.CustomerService;
import database.application.development.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("customer")
@Api(tags = "Customer", description = "Operations about customers")
@Slf4j
public class CustomerController extends Serializer {

    private CustomerService customerService;
    private MailService mailService;

    @Autowired
    public CustomerController(CustomerService customerService, MailService mailService, ObjectMapper mapper) {
        super(mapper);
        this.customerService = customerService;
        this.mailService = mailService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Find Customer by ID", notes = "Implementation for getting Customer by ID")
    public ResponseEntity<Response<Customer>> getCustomerById(@PathVariable int id) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);

        Response<Customer> result = customerService.getCustomerById(new Request<>(header, inputs));
        ResponseEntity<Response<Customer>> response = new ResponseEntity<Response<Customer>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToCustomer());
    }

    @PostMapping("email")
    @ApiOperation(value = "Send an email to the specified customer email", notes = "This takes an Email object, NOT a customer object. Technically, this method could send to any email address, not just a customer's,  but it was placed within the Customer controller as it is currently the only entity with an email address.")
    public void sendMail(@RequestBody Email email) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEmail(email);

        mailService.sendMail(new Request<>(header, inputs));
    }

    @PostMapping
    @ApiOperation(value = "Create Customer", notes = "Implementation for creating a Customer")
    public ResponseEntity<Response<Customer>> createCustomer(@RequestBody Customer customer) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setCustomer(customer);
        Response<Customer> result = customerService.createCustomer(new Request<>(header, inputs));
        ResponseEntity<Response<Customer>> response = new ResponseEntity<Response<Customer>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToCustomer());
    }

    @PutMapping
    @ApiOperation(value = "Update Customer", notes = "Implementation for updating a Customer")
    public ResponseEntity<Response<Customer>> updateCustomer(@RequestBody Customer customer) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setCustomer(customer);
        Response<Customer> result = customerService.updateCustomer(new Request<>(header, inputs));
        ResponseEntity<Response<Customer>> response = new ResponseEntity<Response<Customer>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToCustomer());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Customer", notes = "Implementation for deleting a Customer")
    public ResponseEntity deleteCustomer(@PathVariable int id) {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);
        customerService.deleteCustomer(new Request<>(header, inputs));

        return new ResponseEntity(HttpStatus.OK);
    }
}
