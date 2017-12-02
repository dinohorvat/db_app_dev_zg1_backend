package database.application.development.repository;

import database.application.development.model.domain.Location;
import org.hibernate.Session;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface LocationDao {
    Location getLocationById(int entityId, Session session);

    Location updateLocation(Location location, Session session);

    Location createLocation(Location location, Session session);

    void deleteLocation(Location location, Session session);
}
