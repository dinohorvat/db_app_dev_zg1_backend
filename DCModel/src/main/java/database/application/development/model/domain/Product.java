package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.history.HstProduct;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.Set;

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

    @JsonView(Views.HstProduct.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
    private Set<HstProduct> hstProducts;

}
