package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Employee;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "hstEmployee")
@Table(name = "HST_EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
public class HstEmployee extends HistoryModel {

    public HstEmployee(String description, Employee employee){
       super(description);
       this.username = employee.getUsername();
       this.firstname = employee.getFirstname();
       this.lastname = employee.getLastname();
       this.email = employee.getEmail();
       this.employee = employee;
    }


    @JsonView(Views.PrimitiveField.class)
    @Column(name ="USERNAME")
    private String username;
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="FIRSTNAME")
    private String firstname;
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="LASTNAME")
    private String lastname;
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="EMAIL")
    private String email;

    @JsonView(Views.ComplexFieldEmployee.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee employee;
}
