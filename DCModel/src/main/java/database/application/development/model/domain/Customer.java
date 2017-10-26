package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Entity (name = "customer")
@Table(name = "CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseModel {

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
}
