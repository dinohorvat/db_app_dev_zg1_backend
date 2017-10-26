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

@Entity(name = "product")
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseModel {

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NAME")
    private String name;
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="DESCRIPTION")
    private String description;
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="PRICE")
    private double price;

}
