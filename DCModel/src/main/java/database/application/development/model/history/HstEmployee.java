package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Customer;
import database.application.development.model.domain.Employee;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

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


    @NotNull
    @Size(min=1, max=50)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="USERNAME")
    private String username;

    @NotNull
    @Size(min=1, max=200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="FIRSTNAME")
    private String firstname;

    @NotNull
    @Size(min=1, max=50)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="LASTNAME")
    private String lastname;

    @Null
    @Email
    @Max(50)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="EMAIL")
    private String email;

    @JsonView(Views.ComplexFieldEmployee.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee employee;
}
