package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Employee;
import database.application.development.model.domain.Product;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "hstProduct")
@Table(name = "HST_PRODUCT")
@Getter
@Setter
@NoArgsConstructor
public class HstProduct extends HistoryModel {

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NAME")
    private String name;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="DESCRIPTION")
    private String description;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="PRICE")
    private double price;

    @JsonView(Views.ComplexFieldProduct.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Product product;
}
