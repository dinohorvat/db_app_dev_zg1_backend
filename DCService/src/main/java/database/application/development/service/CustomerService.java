package database.application.development.service;

import database.application.development.model.domain.Customer;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;

public interface CustomerService {

    Response<Customer> getCustomerById(Request<ApplicationInputs> request);

    Response<Customer> createCustomer(Request<ApplicationInputs> request);

    Response<Customer> updateCustomer(Request<ApplicationInputs> request);

    void deleteCustomer(Request<ApplicationInputs> request);
}
