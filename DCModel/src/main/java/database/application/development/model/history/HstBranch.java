package database.application.development.model.history;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.HistoryModel;
import database.application.development.model.domain.Branch;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "hstBranch")
@Table(name = "HST_BRANCH")
@Getter
@Setter
@NoArgsConstructor
public class HstBranch extends HistoryModel {

    public HstBranch(String changeDescription, Branch branch){
        super(changeDescription);
        this.code = branch.getCode();
        this.name = branch.getName();
    }

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="CODE")
    private String code;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NAME")
    private String name;

    @JsonView(Views.ComplexFieldBranch.class)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Branch branch;
}
