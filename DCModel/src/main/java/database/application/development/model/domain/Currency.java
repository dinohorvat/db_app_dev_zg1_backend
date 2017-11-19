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

@Entity(name = "currency")
@Table(name = "CURRENCIES")
@Getter
@Setter
@NoArgsConstructor
public class Currency extends BaseModel{

    @Null
    @Max(45)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NAME")
    private String name;

    @Null
    @Max(5)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="ABBREVIATION")
    private String abbreviation;
}
