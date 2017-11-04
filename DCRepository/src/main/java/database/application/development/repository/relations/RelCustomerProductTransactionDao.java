package database.application.development.repository.relations;

import database.application.development.model.relation.RelCustomerProductTransaction;

public interface RelCustomerProductTransactionDao {

    RelCustomerProductTransaction getRelCustomerProductTransactionById(int entityId);

    RelCustomerProductTransaction updateRelCustomerProductTransaction(RelCustomerProductTransaction RelCustomerProductTransaction);

    RelCustomerProductTransaction createRelCustomerProductTransaction(RelCustomerProductTransaction RelCustomerProductTransaction);

    void deleteRelCustomerProductTransaction(RelCustomerProductTransaction RelCustomerProductTransaction);
}
