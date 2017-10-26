package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Company;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "hst_company")
@Table(name = "HST_COMPANY")
@Getter
@Setter
@NoArgsConstructor
public class HstCompany extends HistoryModel {

    public HstCompany(String changeDescription, Company company) {
        super(changeDescription);
        this.company = company;
        this.name = company.getName();
        this.description = company.getDescription();
        this.operatingCurrency = company.getCurrency();
        this.pointExchangeRate = company.getPointExchangeRate();
    }


    @JsonView(Views.ComplexFieldCompany.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Company company;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="POINTS_CONVERSION_RATE")
    private double pointExchangeRate;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NAME")
    private String name;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="DESCRIPTION")
    private String description;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="OPERATING_CURRENCY")
    private String operatingCurrency;
}
