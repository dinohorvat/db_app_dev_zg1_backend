package database.application.development.service.impl;

import database.application.development.model.domain.Customer;
import database.application.development.model.domain.RewardPoints;
import database.application.development.model.history.HstCustomer;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.CustomerDao;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.repository.hst.HstCustomerDao;
import database.application.development.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl extends ORMConfig implements CustomerService {

    private CustomerDao customerDao;
    private HstCustomerDao hstCustomerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, HstCustomerDao hstCustomerDao) {
        super();
        this.customerDao = customerDao;
        this.hstCustomerDao = hstCustomerDao;
    }

    @Override
    public Response<Customer> getCustomerById(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        Customer customer = customerDao.getCustomerById(request.getBody().getEntityId(), session);
        session.close();
        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public Response<Customer> getCustomerByEmail(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        Customer customer = customerDao.getCustomerByEmail(request.getBody().getCustomerEmail(), session);
        session.close();
        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public Response<Customer> createCustomer(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Customer customer = customerDao.createCustomer(request.getBody().getCustomer(), session);
        addToCustomerHistory("INSERT", customer, session);

        session.getTransaction().commit();
        session.close();

        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public Response<Customer> updateCustomer(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();

        request.getBody().getCustomer().getRewardPoints().forEach(rewardPoints -> {
            rewardPoints.setCustomer(request.getBody().getCustomer());

            if(rewardPoints.getOccurred() == null){
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                rewardPoints.setOccurred(timestamp);
            }
        });

        Customer customer = customerDao.updateCustomer(request.getBody().getCustomer(), session);

        addToCustomerHistory("UPDATE", customer, session);

        session.getTransaction().commit();
        session.close();

        return new Response<>(new OutputHeader(), customer);
    }

    @Override
    public void deleteCustomer(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Customer customer = customerDao.getCustomerById(request.getBody().getEntityId(), session);
        addToCustomerHistory("DELETE", customer, session);

        customerDao.deleteCustomer(customer, session);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Response<List<Customer>> searchCustomer(Request<ApplicationInputs> request){
        Session session = this.getSession();
        List<Customer> customers = customerDao.searchCustomer(request.getBody().getCustomer(), session);
        session.close();
        return new Response<>(new OutputHeader(), customers);
    }

    /**
     * Adds a new row to the HST_CUSTOMER table for this customer object.
     *
     * @param changeDesc The description of the change (INSERT, UPDATE, or DELETE)
     * @param customer The {@link Customer} object which has been changed
     */
    private void addToCustomerHistory(String changeDesc, Customer customer, Session session) {
        HstCustomer hstCustomer = new HstCustomer(changeDesc, customer);
        hstCustomer = hstCustomerDao.createHstCustomer(hstCustomer, session);
        customer.getHstCustomers().add(hstCustomer);
    }
}
