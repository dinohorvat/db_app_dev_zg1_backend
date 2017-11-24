package database.application.development.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.application.development.model.domain.Location;
import database.application.development.model.messages.ApplicationInputs;
import database.application.development.model.messages.InputHeader;
import database.application.development.model.messages.Request;
import database.application.development.model.messages.Response;
import database.application.development.model.util.Views;
import database.application.development.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("location")
@Api(tags = "Location", description = "Operations about location")
@Slf4j
public class LocationController extends Serializer {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService, ObjectMapper mapper) {
        super(mapper);
        this.locationService = locationService;
    }

    @GetMapping(path = "{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find Location by ID", notes = "Implementation for getting Location by ID")
    public ResponseEntity<Response<Location>> getLocationById(@PathVariable int id) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);

        Response<Location> result = locationService.getLocationById(new Request<>(header, inputs));
        ResponseEntity<Response<Location>> response = new ResponseEntity<Response<Location>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToLocation());
    }

    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Location", notes = "Implementation for creating a Location")
    public ResponseEntity<Response<Location>> createLocation(@RequestBody Location location) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setLocation(location);
        Response<Location> result = locationService.createLocation(new Request<>(header, inputs));
        ResponseEntity<Response<Location>> response = new ResponseEntity<Response<Location>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToLocation());
    }

    @PutMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Location", notes = "Implementation for updating a Location")
    public ResponseEntity<Response<Location>> updateLocation(@RequestBody Location location) throws JsonProcessingException {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setLocation(location);
        Response<Location> result = locationService.updateLocation(new Request<>(header, inputs));
        ResponseEntity<Response<Location>> response = new ResponseEntity<Response<Location>>(result, HttpStatus.OK);

        return serializeResponse(result, new Views.RequestToLocation());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete Location", notes = "Implementation for deleting a Location")
    public ResponseEntity deleteLocation(@PathVariable int id) {
        InputHeader header = new InputHeader();
        ApplicationInputs inputs = new ApplicationInputs().setEntityId(id);
        locationService.deleteLocation(new Request<>(header, inputs));

        return new ResponseEntity(HttpStatus.OK);
    }
}
