package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Company;
import database.application.development.model.domain.Currency;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

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
        this.currencyName = company.getCurrency().getName();
        this.currencyAbrv = company.getCurrency().getAbbreviation();
        this.pointExchangeRate = company.getPointExchangeRate();
    }


    @JsonView(Views.ComplexFieldCompany.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("10000000.00")
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="POINTS_CONVERSION_RATE")
    private double pointExchangeRate;

    @NotNull
    @Size(min=1, max=200)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NAME")
    private String name;

    @Null
    @Max(2000)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="DESCRIPTION")
    private String description;

    @Null
    @Max(45)
    @JsonView(Views.ComplexFieldCurrency.class)
    @Column(name ="CURRENCY_NAME")
    private String currencyName;

    @Null
    @Max(5)
    @JsonView(Views.ComplexFieldCurrency.class)
    @Column(name ="CURRENCY_ABRV")
    private String currencyAbrv;
}
