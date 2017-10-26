package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.domain.Transactions;
import database.application.development.model.util.Views;

import javax.persistence.*;

public class HstTransaction {

    @JsonView(Views.ComplexFieldTransaction.class)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="TRANSACTION_ID")
    private Transactions transaction;
}
