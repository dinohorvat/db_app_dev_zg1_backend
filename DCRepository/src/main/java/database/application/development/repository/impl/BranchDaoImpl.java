package database.application.development.repository.impl;

import database.application.development.model.domain.Branch;
import database.application.development.repository.BranchDao;
import database.application.development.repository.configuration.ORMConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Created by dinohorvat on 30/10/2017.
 */
@Slf4j
@Repository
public class BranchDaoImpl implements BranchDao{

    @Autowired
    public BranchDaoImpl(){
    }

    @Override
    public Branch getBranchById(int branchId, Session session) {
        Branch branch = null;
        branch = session.get(Branch.class, branchId);
        if(branch == null) throw new EmptyResultDataAccessException(1);


        return branch;
    }

    @Override
    public Branch updateBranch(Branch branch, Session session) {
        session.update(branch);
        return getBranchById(branch.getId(), session);
    }

    @Override
    public Branch createBranch(Branch branch, Session session) {
        int newEntityId = (int) session.save(branch);
        return getBranchById(newEntityId, session);
    }

    @Override
    public void deleteBranch(Branch branch, Session session) {
        session.delete(branch);
    }
}

