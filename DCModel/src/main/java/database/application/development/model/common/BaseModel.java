package database.application.development.model.common;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class BaseModel {

    @NotNull
    @JsonView(Views.PrimitiveField.class)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="ID")
    int id;
}
