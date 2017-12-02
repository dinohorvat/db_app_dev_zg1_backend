package database.application.development.repository.hst;

import database.application.development.model.history.HstBranch;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstBranchDao {
    HstBranch getHstBranchById(int entityId, Session session);

    HstBranch updateHstBranch(HstBranch hstBranch);

    HstBranch createHstBranch(HstBranch hstBranch, Session session);

    void deleteHstBranch(HstBranch hstBranch);
}
