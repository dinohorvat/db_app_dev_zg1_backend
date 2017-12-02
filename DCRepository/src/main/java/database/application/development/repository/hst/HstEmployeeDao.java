package database.application.development.repository.hst;

import database.application.development.model.history.HstEmployee;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstEmployeeDao {
    HstEmployee getHstEmployeeById(int entityId, Session session);

    HstEmployee updateHstEmployee(HstEmployee hstEmployee);

    HstEmployee createHstEmployee(HstEmployee hstEmployee, Session session);

    void deleteHstEmployee(HstEmployee hstEmployee);
}
