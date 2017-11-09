package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.history.HstCompany;
import database.application.development.model.history.HstEmployee;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "employee")
@Table(name = "EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseModel {

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

    @JsonView(Views.Employee.class)
    @SortNatural
    @OrderBy("id DESC ")
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<Transactions> transactions;

    @JsonView(Views.Employee.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private Branch branch;

    @JsonView(Views.HstEmployee.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<HstEmployee> hstEmployees;
}
