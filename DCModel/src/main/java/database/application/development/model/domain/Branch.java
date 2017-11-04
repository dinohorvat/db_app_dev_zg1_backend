package database.application.development.model.domain;

import com.fasterxml.jackson.annotation.JsonView;
import database.application.development.model.common.BaseModel;
import database.application.development.model.history.HstBranch;
import database.application.development.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "branch")
@Table(name = "BRANCH")
@Getter
@Setter
@NoArgsConstructor
public class Branch extends BaseModel{

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="CODE")
    private String code;

    @JsonView(Views.PrimitiveField.class)
    @Column(name ="NAME")
    private String name;

    @JsonView(Views.Branch.class)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="COMPANY_ID")
    private Company company;

    @JsonView(Views.Location.class)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="LOCATION_ID")
    private Location location;

    @JsonView(Views.HstBranch.class)
    @SortNatural
    @OrderBy("id DESC ")
    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<HstBranch> hstBranches;

    @JsonView(Views.Branch.class)
    @SortNatural
    @OrderBy("id DESC ")
    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Transactions> transactions;


    @JsonView(Views.Branch.class)
    @SortNatural
    @OrderBy("id ASC ")
    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Employee> employees;
}
