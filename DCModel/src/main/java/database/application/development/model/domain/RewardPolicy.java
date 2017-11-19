package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity(name = "rewardPolicy")
@Table(name = "REWARD_POLICY")
@Getter
@Setter
@NoArgsConstructor
public class RewardPolicy extends BaseModel{

    @Null
    @Max(11)
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NUM_POINTS")
    private int numPoints;

    @Null
    @DecimalMax("10000000.00")
    @JsonView(Views.PrimitiveField.class)
    @Column(name ="AMOUNT_REDUCED")
    private double amountReduced;

    @ManyToOne()
    private Company company;

}
