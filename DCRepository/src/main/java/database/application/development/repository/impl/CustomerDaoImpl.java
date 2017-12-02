package database.application.development.repository.impl;

import database.application.development.model.domain.Customer;
import database.application.development.repository.CustomerDao;
import database.application.development.repository.configuration.ORMConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
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
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    public CustomerDaoImpl(){
        super();
    }

    @Override
    public Customer getCustomerById(int customerId, Session session) {
        Customer customer = null;
        customer = session.get(Customer.class, customerId);
        if(customer == null) throw new EmptyResultDataAccessException(1);

        return customer;
    }

    @Override
    public List<Customer> searchCustomer(Customer customer, Session session){
        Criteria cr = session.createCriteria(Customer.class);

        if(customer.getFirstname() != null && !customer.getFirstname().isEmpty()){
            Criterion fn = Restrictions.ilike("firstname", "%"+customer.getFirstname()+"%");
            Disjunction orOperator = Restrictions.or(fn);
            cr.add(orOperator);
        }

        if(customer.getLastname() != null && !customer.getLastname().isEmpty()){
            Criterion ln = Restrictions.ilike("lastname", "%"+customer.getLastname()+"%");
            Disjunction orOperator = Restrictions.or(ln);
            cr.add(orOperator);
        }

        if(customer.getEmail() != null && !customer.getEmail().isEmpty()){
            Criterion email = Restrictions.ilike("email", "%"+customer.getEmail()+"%");
            Disjunction orOperator = Restrictions.or(email);
            cr.add(orOperator);
        }


        List<Customer> results = cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        if(results.size() == 0){
            throw new EmptyResultDataAccessException("No search results, expected at least one",1);
        }
        return results;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Customer getCustomerByEmail(String email, Session session) {

        String hql = "FROM customer WHERE email = :email";
        Customer customer = (Customer) session.createQuery(hql)
                .setString("email", email)
                .uniqueResult();

        return customer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getAllCustomerEmails(Session session) {

        String hql = "SELECT email FROM customer";
        List<String> emails = session.createQuery(hql).list();
        return emails;
    }

    @Override
    public Customer updateCustomer(Customer customer, Session session) {
        session.update(customer);

        return getCustomerById(customer.getId(), session);
    }

    @Override
    public Customer createCustomer(Customer customer, Session session) {
        int newEntityId = (int) session.save(customer);

        return getCustomerById(newEntityId, session);
    }

    @Override
    public void deleteCustomer(Customer customer, Session session) {
        session.delete(customer);
    }
}
