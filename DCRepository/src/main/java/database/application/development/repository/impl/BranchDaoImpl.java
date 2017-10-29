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
public class BranchDaoImpl extends ORMConfig implements BranchDao{

    @Autowired
    public BranchDaoImpl(){
        super();
    }

    @Override
    public Branch getBranchById(int branchId) {
        Session session = this.getSession();
        Branch branch = null;
        Transaction transaction = session.beginTransaction();
        branch = session.get(Branch.class, branchId);
        if(branch == null) throw new EmptyResultDataAccessException(1);
        transaction.commit();
        session.close();

        return branch;
    }

    @Override
    public Branch updateBranch(Branch branch) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(branch);
        transaction.commit();
        session.close();

        return getBranchById(branch.getId());
    }

    @Override
    public Branch createBranch(Branch branch) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(branch);
        transaction.commit();
        session.close();

        return getBranchById(newEntityId);
    }

    @Override
    public void deleteBranch(Branch branch) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(branch);
        transaction.commit();
        session.close();
    }
}

