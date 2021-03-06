package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.history.HstCompany;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
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

    public Company(String name, String description, Currency currency, Location hq_location, double pointExchangeRate) {
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.hqLocation = hq_location;
        this.pointExchangeRate = pointExchangeRate;
    }

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NAME")
    private String name;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="DESCRIPTION")
    private String description;

    @JsonView(Views.Company.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_ID")
    private Currency currency;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="POINTS_CONVERSION_RATE")
    private double pointExchangeRate;

    @JsonView(Views.Location.class)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="HQ_LOCATION")
    private Location hqLocation;

    @JsonView(Views.HstCompany.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<HstCompany> hstCompanies;

    @JsonView(Views.Company.class)
    @SortNatural
    @OrderBy("id ASC ")
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Branch> branches;

    @JsonView(Views.Company.class)
    @SortNatural
    @OrderBy("id ASC ")
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Set<RewardPolicy> policies;

}
