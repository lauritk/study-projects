package fi.jyu.soa.rest.security.demo4.controllers;

import com.mongodb.WriteResult;
import fi.jyu.soa.rest.security.demo4.models.LocationType;
import fi.jyu.soa.rest.security.demo4.models.Location;
import fi.jyu.soa.rest.security.demo4.repositories.LocationTypeRepository;
import fi.jyu.soa.rest.security.demo4.repositories.LocationRepository;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("locations/{locationId}/type")
@RestController
public class LocationTypeController {
    
    @Autowired
    private LocationTypeRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    @SuppressWarnings("unchecked")
    public LocationType getLocationTypeByLocation(@PathVariable("locationId") ObjectId locationId) {
        Location location = (Location) locationRepository.read(locationId);
        LocationType locationType = location.getLocationType();
        return locationType;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public LocationType modifyLocationTypeById(@PathVariable("locationId") ObjectId locationId,
                                     @Valid @RequestBody LocationType locationType) {
        Location location = (Location) locationRepository.read(locationId);
        try {
            LocationType old = (LocationType) repository.read(location.getLocationType().get_id());
            locationType.set_id(old.get_id());
            return repository.update(locationType);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public LocationType createLocationType(@PathVariable("locationId") ObjectId locationId,
                                 @Valid @RequestBody LocationType locationType) {
        Key<LocationType> key = repository.create(locationType);
        ObjectId id = new ObjectId(key.getId().toString());
        Location location = (Location) locationRepository.read(locationId);
        location.setLocationType(locationType);
        locationRepository.update(location);
        return (LocationType) repository.read(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public WriteResult deleteLocationType(@PathVariable("locationId") ObjectId locationId) {
        Location location = (Location) locationRepository.read(locationId);
        LocationType locationType = location.getLocationType();
        UpdateOperations<Location> update = locationRepository.getDatastore().createUpdateOperations(Location.class).unset("locationType");
        locationRepository.getDatastore().update(location, update);
        return repository.delete(locationType);
    }
}
