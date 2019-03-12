package fi.jyu.soa.rest.security.demo4.controllers;

import fi.jyu.soa.rest.security.demo4.models.Location;
import fi.jyu.soa.rest.security.demo4.repositories.LocationRepository;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("locations")
public class LocationController {

    @Autowired
    private LocationRepository repository;

    @PreAuthorize("hasAnyRole('USER, ADMIN')")
    @GetMapping
    @SuppressWarnings("unchecked")
    public List<Location> listLocations(@RequestParam(required = false, value = "locationType") List<String> locationType) {
        Map<String, List<String>> filter = new HashMap<>();
        if (locationType != null) {
            filter.put("locationType", locationType);
        }
        return this.repository.getCollection(filter);
    }

    @GetMapping("{id}")
    public Location getLocationById(@PathVariable("id") ObjectId id) {
        Location location = (Location) repository.read(id);
        return location;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public Location modifyLocationById(@PathVariable("id") ObjectId id,
                                     @Valid @RequestBody Location location) {
        try {
            Location old = (Location) repository.read(id);
            location.set_id(old.get_id());
            return repository.update(location);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public Location createLocation(@Valid @RequestBody Location location) {
        Key<Location> key = repository.create(location);
        ObjectId id = new ObjectId(key.getId().toString());
        return (Location) repository.read(id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public void deleteLocation(@PathVariable("id") ObjectId id) {
        // TODO make recursive deletion so that objects that are not needed are removed too?
        // TODO Deletion actually could be just a flag for enabling restoring deletion.
        repository.delete(repository.delete(getLocationById(id)));
    }

}
