package database.application.development.repository;

import database.application.development.model.domain.Customer;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
public interface CustomerDao {
    Customer getCustomerById(int entityId);

    Customer updateCustomer(Customer customer);

    Customer createCustomer(Customer customer);

    void deleteCustomer(Customer customer);
}
