package database.application.development.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.util.DateFormatter;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class HistoryModel extends BaseModel{

    public HistoryModel(String changeDesc) {
        this.dateChanged = getCurrentTimeStamp();
        this.changeDesc = changeDesc;
    }

    @JsonView(Views.PrimitiveField.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.LOCAL_DATE_TIME_FORMAT)
    @Column(name ="DATE_CHANGED")
    private Timestamp dateChanged;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="CHANGE_DESC")
    private String changeDesc;


    private Timestamp getCurrentTimeStamp(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}
