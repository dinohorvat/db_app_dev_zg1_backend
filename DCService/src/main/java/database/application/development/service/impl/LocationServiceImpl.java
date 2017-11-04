package database.application.development.service.impl;

import database.application.development.model.domain.Location;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.LocationDao;
import database.application.development.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private LocationDao locationDao;

    @Autowired
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public Response<Location> getLocationById(Request<ApplicationInputs> request) {
        Location location = locationDao.getLocationById(request.getBody().getEntityId());
        return new Response<>(new OutputHeader(), location);
    }

    @Override
    public Response<Location> createLocation(Request<ApplicationInputs> request) {
        Location location = locationDao.createLocation(request.getBody().getLocation());
        return new Response<>(new OutputHeader(), location);
    }

    @Override
    public Response<Location> updateLocation(Request<ApplicationInputs> request) {
        Location location = locationDao.updateLocation(request.getBody().getLocation());
        return new Response<>(new OutputHeader(), location);
    }

    @Override
    public void deleteLocation(Request<ApplicationInputs> request) {
        Location location = locationDao.getLocationById(request.getBody().getEntityId());
        locationDao.deleteLocation(location);
    }
}
