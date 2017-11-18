package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Customer;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="FIRSTNAME")
    private String firstname;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="LASTNAME")
    private String lastname;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="PHONE")
    private String phone;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="ADDRESS")
    private String address;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="CITY")
    private String city;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="EMAIL")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Customer customer;
}
