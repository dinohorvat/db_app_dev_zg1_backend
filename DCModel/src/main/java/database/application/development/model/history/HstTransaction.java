package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Branch;
import database.application.development.model.domain.Employee;
import database.application.development.model.domain.Transactions;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "hstTransaction")
@Table(name = "HST_TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
public class HstTransaction  extends HistoryModel {


//    @JsonView(Views.ComplexFieldTransaction.class)
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Employee em;
//
//    @JsonView(Views.ComplexFieldTransaction.class)
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Branch branch;
//
//    @JsonView(Views.ComplexFieldTransaction.class)
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Transactions transaction;
}
