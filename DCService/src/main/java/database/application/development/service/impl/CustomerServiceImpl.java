package database.application.development.service.impl;

import database.application.development.model.domain.Customer;
import database.application.development.model.history.HstCustomer;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.CustomerDao;
import database.application.development.repository.hst.HstCustomerDao;
import database.application.development.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private HstCustomerDao hstCustomerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, HstCustomerDao hstCustomerDao) {
        this.customerDao = customerDao;
        this.hstCustomerDao = hstCustomerDao;
    }

    @Override
    public Response<Customer> getCustomerById(Request<ApplicationInputs> request) {
        Customer customer = customerDao.getCustomerById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public Response<Customer> getCustomerByEmail(Request<ApplicationInputs> request) {
        Customer customer = customerDao.getCustomerByEmail(request.getBody().getCustomerEmail());
        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public Response<Customer> createCustomer(Request<ApplicationInputs> request) {
        Customer customer = customerDao.createCustomer(request.getBody().getCustomer());

        addToCustomerHistory("INSERT", customer);

        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public Response<Customer> updateCustomer(Request<ApplicationInputs> request) {
        Customer customer = customerDao.updateCustomer(request.getBody().getCustomer());

        addToCustomerHistory("UPDATE", customer);

        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public void deleteCustomer(Request<ApplicationInputs> request) {
        Customer customer = customerDao.getCustomerById(request.getBody().getEntityId());

        addToCustomerHistory("DELETE", customer);

        customerDao.deleteCustomer(customer);
    }

    /**
     * Adds a new row to the HST_CUSTOMER table for this customer object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param customer The {@link Customer} object which has been changed
     */
    private void addToCustomerHistory(String changeDesc, Customer customer) {
        HstCustomer hstCustomer = new HstCustomer(changeDesc, customer);
        hstCustomer = hstCustomerDao.createHstCustomer(hstCustomer);
        customer.getHstCustomers().add(hstCustomer);
    }
}
