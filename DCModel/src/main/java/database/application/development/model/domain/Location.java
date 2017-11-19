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
import javax.validation.constraints.Max;
import javax.validation.constraints.Null;

@Entity(name = "location")
@Table(name = "LOCATION")
@Getter
@Setter
@NoArgsConstructor
public class Location extends BaseModel{

    public Location(String country, String state, String city, String address, String zip, String additional) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zip = zip;
        this.additional = additional;
    }

    @Null
    @Max(200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="COUNTRY")
    private String country;

    @Null
    @Max(200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="STATE")
    private String state;

    @Null
    @Max(200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="CITY")
    private String city;

    @Null
    @Max(200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="ADDRESS")
    private String address;

    @Null
    @Max(10)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="ZIP")
    private String zip;

    @Null
    @Max(2000)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="ADDITIONAL")
    private String additional;

}
