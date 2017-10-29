package database.application.development.repository;

import database.application.development.model.domain.Branch;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface BranchDao {
    Branch getBranchById(int entityId);

    Branch updateBranch(Branch branch);

    Branch createBranch(Branch branch);

    void deleteBranch(Branch branch);
}
