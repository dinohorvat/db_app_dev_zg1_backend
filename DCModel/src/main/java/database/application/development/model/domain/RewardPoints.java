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

@Entity(name = "rewardPoints")
@Table(name = "REWARD_POINTS")
@Getter
@Setter
@NoArgsConstructor
public class RewardPoints extends BaseModel {

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="AMOUNT")
    private int amount;
}
