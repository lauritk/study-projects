package fi.jyu.soa.rest.security.demo4.controllers;

import com.mongodb.WriteResult;
import fi.jyu.soa.rest.security.demo4.models.Location;
import fi.jyu.soa.rest.security.demo4.models.Coordinate;
import fi.jyu.soa.rest.security.demo4.repositories.LocationRepository;
import fi.jyu.soa.rest.security.demo4.repositories.CoordinateRepository;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("locations/{locationId}/coordinate")
@RestController
public class CoordinateController {
    @Autowired
    private CoordinateRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    @SuppressWarnings("unchecked")
    public Coordinate getCoordinateByLocation(@PathVariable("locationId") ObjectId locationId) {
        Location location = (Location) locationRepository.read(locationId);
        Coordinate coordinate = location.getCoordinate();
        return coordinate;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Coordinate modifyCoordinateById(@PathVariable("locationId") ObjectId locationId,
                                       @Valid @RequestBody Coordinate coordinate) {
        Location location = (Location) locationRepository.read(locationId);
        try {
            Coordinate old = (Coordinate) repository.read(location.getCoordinate().get_id());
            coordinate.set_id(old.get_id());
            return repository.update(coordinate);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public Coordinate createCoordinate(@PathVariable("locationId") ObjectId locationId,
                                   @Valid @RequestBody Coordinate coordinate) {
        Key<Coordinate> key = repository.create(coordinate);
        ObjectId id = new ObjectId(key.getId().toString());
        Location location = (Location) locationRepository.read(locationId);
        location.setCoordinate(coordinate);
        locationRepository.update(location);
        return (Coordinate) repository.read(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public WriteResult deleteCoordinate(@PathVariable("locationId") ObjectId locationId) {
        Location location = (Location) locationRepository.read(locationId);
        Coordinate coordinate = location.getCoordinate();
        UpdateOperations<Location> update = locationRepository.getDatastore().createUpdateOperations(Location.class).unset("coordinate");
        locationRepository.getDatastore().update(location, update);
        return repository.delete(coordinate);
    }
}
