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

@Entity(name = "transaction")
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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee employee;

    @JsonView(Views.Transaction.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Branch branch;

    @JsonView(Views.Transaction.class)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RewardPoints points;

    @JsonView(Views.Transaction.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "TRANSACTION_ID")
    private Set<RelCustomerProductTransaction> dependencies;


//    @JsonView(Views.HstTransaction.class)
//    @SortNatural
//    @OrderBy("id DESC")
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "TRANSACTION_ID")
//    private Set<HstTransaction> hstTransactions;

    @JsonView(Views.Transaction.class)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="DATE_ID")
    private DcsDate dcsDate;


}
