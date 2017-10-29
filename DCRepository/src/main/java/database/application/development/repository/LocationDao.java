package database.application.development.repository;

import database.application.development.model.domain.Location;

/**
 * Created by dinohorvat on 30/10/2017.
 */
public interface LocationDao {
    Location getLocationById(int entityId);

    Location updateLocation(Location location);

    Location createLocation(Location location);

    void deleteLocation(Location location);
}
