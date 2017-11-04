package database.application.development.model.relation;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.domain.Customer;
import database.application.development.model.domain.Product;
import database.application.development.model.domain.Transactions;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "RelCustomerProductTransaction")
@Table(name = "REL_CUSTOMER_PRODUCT_TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
public class RelCustomerProductTransaction extends BaseModel{

    @JsonView(Views.ComplexFieldTransaction.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Transactions transaction;

    @JsonView(Views.ComplexFieldProduct.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @JsonView(Views.ComplexFieldCustomer.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Customer customer;
}
