package database.application.development.repository.hst;

import database.application.development.model.history.HstBranch;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface HstBranchDao {
    HstBranch getHstBranchById(int entityId);

    HstBranch updateHstBranch(HstBranch hstBranch);

    HstBranch createHstBranch(HstBranch hstBranch);

    void deleteHstBranch(HstBranch hstBranch);
}
