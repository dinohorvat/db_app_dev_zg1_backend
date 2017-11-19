package database.application.development.repository.impl;

import database.application.development.model.domain.Customer;
import database.application.development.repository.CustomerDao;
import database.application.development.repository.configuration.ORMConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Slf4j
@Repository
public class CustomerDaoImpl extends ORMConfig implements CustomerDao {

    @Autowired
    public CustomerDaoImpl(){
        super();
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Session session = this.getSession();
        Customer customer = null;
        Transaction transaction = session.beginTransaction();
        customer = session.get(Customer.class, customerId);
        if(customer == null) throw new EmptyResultDataAccessException(1);
        transaction.commit();
        session.close();

        return customer;
    }

//    TODO: create search
//    public List<Customer> searchCustomer(Customer customer){
//        Session session = this.getSession();
//
//        Criteria cr = session.createCriteria(Customer.class);
//        cr.add(Restrictions.ilike("firstname", "%"+customer.getFirstname()+"%"));
//        cr.add(Restrictions.ilike("lastname", "%"+customer.getLastname()+"%"));
//        cr.add(Restrictions.ilike("email", "%"+customer.getEmail()+"%"));
//
//        List<Customer> results = cr.list();
//
//        return results;
//    }

    @Override
    @SuppressWarnings("unchecked")
    public Customer getCustomerByEmail(String email) {
        Session session = this.getSession();

        String hql = "FROM customer WHERE email = :email";
        Customer customer = (Customer) session.createQuery(hql)
                .setString("email", email)
                .uniqueResult();

        session.close();
        return customer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getAllCustomerEmails() {
        Session session = this.getSession();

        String hql = "SELECT email FROM customer";
        List<String> emails = session.createQuery(hql).list();

        session.close();
        return emails;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();

        return getCustomerById(customer.getId());
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(customer);
        transaction.commit();
        session.close();

        return getCustomerById(newEntityId);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
        session.close();
    }
}
