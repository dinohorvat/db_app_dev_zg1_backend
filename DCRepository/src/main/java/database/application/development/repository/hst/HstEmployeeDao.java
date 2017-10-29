package database.application.development.repository.hst;

import database.application.development.model.history.HstEmployee;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstEmployeeDao {
    HstEmployee getHstEmployeeById(int entityId);

    HstEmployee updateHstEmployee(HstEmployee hstEmployee);

    HstEmployee createHstEmployee(HstEmployee hstEmployee);

    void deleteHstEmployee(HstEmployee hstEmployee);
}
