package database.application.development.repository.impl;

import database.application.development.model.domain.Customer;
import database.application.development.repository.CustomerDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Slf4j
@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Customer getCustomerById() {
        return null;
    }
}
