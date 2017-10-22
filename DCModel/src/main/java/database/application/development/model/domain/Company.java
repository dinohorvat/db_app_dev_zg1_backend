package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.history.HstCompany;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Entity (name = "company")
@Table(name = "COMPANY")
@Getter
@Setter
@NoArgsConstructor
public class Company extends BaseModel{

    public Company(String name, String description, String currency, Location hq_location) {
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.hqLocation = hq_location;
    }

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NAME")
    private String name;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="DESCRIPTION")
    private String description;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="OPERATING_CURRENCY")
    private String currency;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="POINTS_CONVERSION_RATE")
    private double pointExchangeRate;

    @JsonView(Views.Location.class)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="HQ_LOCATION")
    private Location hqLocation;

    @JsonView(Views.HstCompany.class)
    @SortNatural
    @OrderBy("id ASC")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID")
    private Set<HstCompany> hstCompanies;

}
