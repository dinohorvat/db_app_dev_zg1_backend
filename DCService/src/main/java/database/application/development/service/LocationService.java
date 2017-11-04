package database.application.development.service;

import database.application.development.model.domain.Location;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;

public interface LocationService {

    Response<Location> getLocationById(Request<ApplicationInputs> request);

    Response<Location> createLocation(Request<ApplicationInputs> request);

    Response<Location> updateLocation(Request<ApplicationInputs> request);

    void deleteLocation(Request<ApplicationInputs> request);
}
