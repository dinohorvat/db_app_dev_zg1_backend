package database.application.development.repository.relations;

import database.application.development.model.relation.RelCustomerProductTransaction;
import org.hibernate.Session;

public interface RelCustomerProductTransactionDao {

    RelCustomerProductTransaction getRelCustomerProductTransactionById(int entityId, Session session);

    RelCustomerProductTransaction updateRelCustomerProductTransaction(RelCustomerProductTransaction RelCustomerProductTransaction, Session session);

    RelCustomerProductTransaction createRelCustomerProductTransaction(RelCustomerProductTransaction RelCustomerProductTransaction, Session session);

    void deleteRelCustomerProductTransaction(RelCustomerProductTransaction RelCustomerProductTransaction, Session session);
}
