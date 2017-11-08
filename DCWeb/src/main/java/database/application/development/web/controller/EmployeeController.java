package database.application.development.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.application.development.model.domain.Employee;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.InputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.util.Views;
import database.application.development.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("employee")
@Api(tags = "Employee", description = "Operations about employees")
@Slf4j
public class EmployeeController extends Serializer {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ObjectMapper mapper) {
        super(mapper);
        this.employeeService = employeeService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Find Employee by ID", notes = "Implementation for getting an Employee by their ID")
    public ResponseEntity<Response<Employee>> getEmployeeById(@PathVariable int id) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);

        Response<Employee> result = employeeService.getEmployeeById(new Request<>(header, inputs));

        return serializeResponse(result, new Views.RequestToEmployee());
    }

    @PostMapping
    @ApiOperation(value = "Create Employee", notes = "Implementation for creating an Employee")
    public ResponseEntity<Response<Employee>> createEmployee(@RequestBody Employee employee) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEmployee(employee);

        Response<Employee> result = employeeService.createEmployee(new Request<>(header, inputs));

        return serializeResponse(result, new Views.RequestToEmployee());
    }

    @PutMapping
    @ApiOperation(value = "Update Employee", notes = "Implementation for updating an Employee")
    public ResponseEntity<Response<Employee>> updateEmployee(@RequestBody Employee employee) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEmployee(employee);

        Response<Employee> result = employeeService.updateEmployee(new Request<>(header, inputs));

        return serializeResponse(result, new Views.RequestToEmployee());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Employee", notes = "Implementation for deleting an Employee")
    public ResponseEntity deleteEmployee(@PathVariable int id) {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);
        employeeService.deleteEmployee(new Request<>(header, inputs));

        return new ResponseEntity(HttpStatus.OK);
    }
}
