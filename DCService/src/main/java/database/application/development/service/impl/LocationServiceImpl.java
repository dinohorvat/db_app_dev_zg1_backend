package database.application.development.service.impl;

import database.application.development.model.domain.Location;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.OutputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.repository.LocationDao;
import database.application.development.repository.configuration.ORMConfig;
import database.application.development.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocationServiceImpl extends ORMConfig implements LocationService {

    private LocationDao locationDao;

    @Autowired
    public LocationServiceImpl(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    public Response<Location> getLocationById(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        Location location = locationDao.getLocationById(request.getBody().getEntityId(), session);
        session.close();
        return new Response<>(new OutputHeader(), location);
    }

    @Override
    public Response<Location> createLocation(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Location location = locationDao.createLocation(request.getBody().getLocation(), session);
        session.getTransaction().commit();
        session.close();
        return new Response<>(new OutputHeader(), location);
    }

    @Override
    public Response<Location> updateLocation(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Location location = locationDao.updateLocation(request.getBody().getLocation(), session);
        session.getTransaction().commit();
        session.close();
        return new Response<>(new OutputHeader(), location);
    }

    @Override
    public void deleteLocation(Request<ApplicationInputs> request) {
        Session session = this.getSession();
        session.beginTransaction();
        Location location = locationDao.getLocationById(request.getBody().getEntityId(), session);
        locationDao.deleteLocation(location, session);
        session.getTransaction().commit();
        session.close();
    }
}
