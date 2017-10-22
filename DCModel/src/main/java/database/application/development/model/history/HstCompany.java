package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.domain.Company;
import database.application.development.model.domain.Location;
import database.application.development.model.util.DateFormatter;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name = "hst_company")
@Table(name = "HST_COMPANY")
@Getter
@Setter
@NoArgsConstructor
public class HstCompany extends BaseModel{

    public HstCompany(Timestamp dateChanged, String changeDescription, Company company) {
        this.dateChanged = this.getCurrentTimeStamp();
        this.company = company;
        this.changeDescription = changeDescription;
        this.name = company.getName();
        this.description = company.getDescription();
        this.operatingCurrency = company.getCurrency();
        this.pointExchangeRate = company.getPointExchangeRate();
    }

    @JsonView(Views.PrimitiveField.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.LOCAL_DATE_TIME_FORMAT)
    @Column(name ="DATE_CHANGED")
    private Timestamp dateChanged;

    @JsonView(Views.ComplexFieldCompany.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="CHANGE_DESC")
    private String changeDescription;

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

    private Timestamp getCurrentTimeStamp(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}
