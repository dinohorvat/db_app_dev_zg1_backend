package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Customer;
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

@Entity(name = "hstCustomer")
@Table(name = "HST_CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
public class HstCustomer  extends HistoryModel {

    public HstCustomer(String description, Customer customer){
        super(description);
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.phone = customer.getPhone();
        this.address = customer.getAddress();
        this.city = customer.getCity();
        this.email = customer.getEmail();
        this.customer = customer;
    }

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Customer customer;
}
