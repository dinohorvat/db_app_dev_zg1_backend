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
import java.sql.Timestamp;

@Entity(name = "hstTransaction")
@Table(name = "HST_TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
public class HstTransaction  extends HistoryModel {

    @JsonView(Views.PrimitiveField.class)
    @Enumerated(EnumType.STRING)
    @Column(name ="STATUS")
    private TransactionStatus status;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="TOTAL_PRICE")
    private double totalPrice;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="EMPLOYEE_ID")
    private int employeeId;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="BRANCH_ID")
    private int branchId;

    @JsonView(Views.ComplexFieldProduct.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Transactions transaction;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="EMPLOYEE_USERNAME")
    private String employeeUsername;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="BRANCH_NAME")
    private String branchName;

    @JsonView(Views.PrimitiveField.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.LOCAL_DATE_TIME_FORMAT)
    @Column(name ="TRANSACTION_EXP_COMPLETED")
    private Timestamp transactionExpCompleted;

    @JsonView(Views.PrimitiveField.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.LOCAL_DATE_TIME_FORMAT)
    @Column(name ="TRANSACTION_PLACED")
    private Timestamp transactionPlaced;


}
