package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Branch;
import database.application.development.model.domain.Employee;
import database.application.development.model.domain.Transactions;
import database.application.development.model.util.DateFormatter;
import database.application.development.model.util.TransactionStatus;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Entity(name = "hstTransaction")
@Table(name = "HST_TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
public class HstTransaction  extends HistoryModel {

    public HstTransaction(String changeDesc, Transactions transactions) {
        super(changeDesc);
        this.status = transactions.getStatus();
        this.totalPrice = transactions.getTotalPrice();
        this.employeeId = transactions.getEmployee().getId();
        this.branchId = transactions.getBranch().getId();
        this.transaction = transactions;
        this.employeeUsername = transactions.getEmployee().getUsername();
        this.branchName = transactions.getBranch().getName();
        this.transactionExpCompleted = transactions.getDcsDate().getTransactionExpCompleted();
        this.transactionPlaced = transactions.getDcsDate().getTransactionPlaced();
    }

    @JsonView(Views.PrimitiveField.class)
    @Enumerated(EnumType.STRING)
    @Column(name ="STATUS")
    private TransactionStatus status;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("10000000.00")
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="TOTAL_PRICE")
    private double totalPrice;

    @NotNull
    @Min(1)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="EMPLOYEE_ID")
    private int employeeId;

    @NotNull
    @Min(1)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="BRANCH_ID")
    private int branchId;

    @JsonView(Views.ComplexFieldTransaction.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TRANSACTION_ID")
    private Transactions transaction;

    @NotNull
    @Size(min=1, max=50)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="EMPLOYEE_USERNAME")
    private String employeeUsername;

    @Null
    @Max(200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="BRANCH_NAME")
    private String branchName;

    @Null
    @JsonView(Views.PrimitiveField.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.LOCAL_DATE_TIME_FORMAT)
    @Column(name ="TRANSACTION_EXP_COMPLETED")
    private Timestamp transactionExpCompleted;

    @Null
    @JsonView(Views.PrimitiveField.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.LOCAL_DATE_TIME_FORMAT)
    @Column(name ="TRANSACTION_PLACED")
    private Timestamp transactionPlaced;
}
