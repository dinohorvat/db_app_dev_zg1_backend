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
import javax.validation.constraints.*;
import java.util.Set;

@Entity(name = "product")
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseModel {

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

    @JsonView(Views.HstProduct.class)
    @SortNatural
    @OrderBy("id DESC")
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<HstProduct> hstProducts;

}
