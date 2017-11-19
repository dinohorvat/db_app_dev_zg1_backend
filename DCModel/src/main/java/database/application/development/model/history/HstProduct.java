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
import javax.validation.constraints.*;

@Entity(name = "hstProduct")
@Table(name = "HST_PRODUCT")
@Getter
@Setter
@NoArgsConstructor
public class HstProduct extends HistoryModel {

    public HstProduct(String changeDesc, Product product) {
        super(changeDesc);
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.product = product;
    }

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

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("10000000.00")
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="PRICE")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
