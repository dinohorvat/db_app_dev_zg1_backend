package database.application.development.repository;

import database.application.development.model.domain.Customer;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
public interface CustomerDao {
    Customer getCustomerById(int entityId, Session session);

    List<Customer> searchCustomer(Customer customer, Session session);

    Customer getCustomerByEmail(String email, Session session);

    List<String> getAllCustomerEmails(Session session);

    Customer updateCustomer(Customer customer, Session session);

    Customer createCustomer(Customer customer, Session session);

    void deleteCustomer(Customer customer, Session session);
}
