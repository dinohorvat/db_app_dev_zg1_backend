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
public class LocationDaoImpl implements LocationDao {
    @Autowired
    public LocationDaoImpl(){
        super();
    }

    @Override
    public Location getLocationById(int locationId, Session session) {
        Location location = null;
        location = session.get(Location.class, locationId);
        if(location == null) throw new EmptyResultDataAccessException(1);

        return location;
    }

    @Override
    public Location updateLocation(Location location, Session session) {
        session.update(location);

        return getLocationById(location.getId(), session);
    }

    @Override
    public Location createLocation(Location location, Session session) {
        int newEntityId = (int) session.save(location);
        return getLocationById(newEntityId, session);
    }

    @Override
    public void deleteLocation(Location location, Session session) {
        session.delete(location);
    }
}


