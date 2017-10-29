package database.application.development.repository.impl;

import database.application.development.model.domain.Location;
import database.application.development.repository.LocationDao;
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
public class LocationDaoImpl extends ORMConfig implements LocationDao {
    @Autowired
    public LocationDaoImpl(){
        super();
    }

    @Override
    public Location getLocationById(int locationId) {
        Session session = this.getSession();
        Location location = null;
        Transaction transaction = session.beginTransaction();
        location = session.get(Location.class, locationId);
        if(location == null) throw new EmptyResultDataAccessException(1);
        transaction.commit();
        session.close();

        return location;
    }

    @Override
    public Location updateLocation(Location location) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(location);
        transaction.commit();
        session.close();

        return getLocationById(location.getId());
    }

    @Override
    public Location createLocation(Location location) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        int newEntityId = (int) session.save(location);
        transaction.commit();
        session.close();

        return getLocationById(newEntityId);
    }

    @Override
    public void deleteLocation(Location location) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(location);
        transaction.commit();
        session.close();
    }
}


