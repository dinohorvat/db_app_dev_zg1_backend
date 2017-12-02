package database.application.development.repository;

import database.application.development.model.domain.Branch;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface BranchDao {
    Branch getBranchById(int entityId, Session session);

    Branch updateBranch(Branch branch, Session session);

    Branch createBranch(Branch branch, Session session);

    void deleteBranch(Branch branch, Session session);
}
