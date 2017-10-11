package database.application.development.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
}
