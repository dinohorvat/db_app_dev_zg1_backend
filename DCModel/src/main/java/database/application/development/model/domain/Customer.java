package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.history.HstCustomer;
import database.application.development.model.history.HstTransaction;
import database.application.development.model.relation.RelCustomerProductTransaction;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Entity (name = "customer")
@Table(name = "CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseModel {

    @NotNull
    @Size(min=1, max=200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="FIRSTNAME")
    private String firstname;

    @NotNull
    @Size(min=1, max=200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="LASTNAME")
    private String lastname;

    @Null
    @Max(50)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="PHONE")
    private String phone;

    @NotNull
    @Size(min=1, max=200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="ADDRESS")
    private String address;

    @Null
    @Max(45)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="CITY")
    private String city;

    @Null
    @Email
    @Max(200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="EMAIL")
    private String email;

    @JsonView(Views.Customer.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<RelCustomerProductTransaction> transactions;

    @JsonView(Views.Customer.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<RewardPoints> rewardPoints;

    @JsonView(Views.HstCustomer.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<HstCustomer> hstCustomers;
}
