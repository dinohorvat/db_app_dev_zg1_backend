package database.application.development.service.impl;

import database.application.development.model.domain.Customer;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.CustomerDao;
import database.application.development.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Response<Customer> getCustomerById(Request<ApplicationInputs> request) {
        Customer customer = customerDao.getCustomerById(request.getBody().getEntityId());

        // TODO: 11/2/2017 insert hst

        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public Response<Customer> createCustomer(Request<ApplicationInputs> request) {
        Customer customer = customerDao.createCustomer(request.getBody().getCustomer());

        // TODO: 11/2/2017 insert hst

        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public Response<Customer> updateCustomer(Request<ApplicationInputs> request) {
        Customer customer = customerDao.updateCustomer(request.getBody().getCustomer());

        // TODO: 11/2/2017 insert hst

        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public void deleteCustomer(Request<ApplicationInputs> request) {
        Customer customer = customerDao.getCustomerById(request.getBody().getEntityId());

        // TODO: 11/2/2017 insert hst

        customerDao.deleteCustomer(customer);
    }
}
