package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.util.DateFormatter;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name = "rewardPoints")
@Table(name = "REWARD_POINTS")
@Getter
@Setter
@NoArgsConstructor
public class RewardPoints extends BaseModel {

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="AMOUNT")
    private int amount;

    @JsonView(Views.PrimitiveField.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.LOCAL_DATE_TIME_FORMAT)
    @Column(name ="OCCURRED")
    private Timestamp occurred;
}
