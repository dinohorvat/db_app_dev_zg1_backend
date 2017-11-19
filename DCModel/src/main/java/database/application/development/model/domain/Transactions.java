package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.history.HstTransaction;
import database.application.development.model.relation.RelCustomerProductTransaction;
import database.application.development.model.util.TransactionStatus;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "transactions")
@Table(name = "TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
public class Transactions extends BaseModel {


    @JsonView(Views.PrimitiveField.class)
    @Column(name ="TOTAL_PRICE")
    private double totalPrice;

    @JsonView(Views.PrimitiveField.class)
    @Enumerated(EnumType.STRING)
    @Column(name ="STATUS")
    private TransactionStatus status;

    @JsonView(Views.Transaction.class)
    @OneToOne(fetch = FetchType.EAGER)
    private Employee employee;

    @JsonView(Views.Transaction.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private Branch branch;

    //TODO: possibly include reward points

    @JsonView(Views.Transaction.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<RelCustomerProductTransaction> transactionItems;


    @JsonView(Views.Transaction.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<HstTransaction> hstTransactions;

    @JsonView(Views.Transaction.class)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "DATE_ID")
    private DcsDate dcsDate;


}
