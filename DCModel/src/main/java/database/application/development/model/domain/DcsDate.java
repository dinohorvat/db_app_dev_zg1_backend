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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name = "dcsDate")
@Table(name = "DATE")
@Getter
@Setter
@NoArgsConstructor
public class DcsDate extends BaseModel{

    @JsonView(Views.PrimitiveField.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.LOCAL_DATE_TIME_FORMAT)
    @Column(name ="TRANSACTION_PLACED")
    private Timestamp transactionPlaced;

    @JsonView(Views.PrimitiveField.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.LOCAL_DATE_TIME_FORMAT)
    @Column(name ="TRANSACTION_EXP_COMPLETED")
    private Timestamp transactionExpCompleted;
}
