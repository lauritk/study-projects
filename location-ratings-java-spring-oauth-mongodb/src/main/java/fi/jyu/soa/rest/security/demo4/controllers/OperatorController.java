package fi.jyu.soa.rest.security.demo4.controllers;

import com.mongodb.WriteResult;
import fi.jyu.soa.rest.security.demo4.models.Operator;
import fi.jyu.soa.rest.security.demo4.models.Location;
import fi.jyu.soa.rest.security.demo4.repositories.OperatorRepository;
import fi.jyu.soa.rest.security.demo4.repositories.LocationRepository;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("locations/{locationId}/operator")
@RestController
public class OperatorController {
    @Autowired
    private OperatorRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    @SuppressWarnings("unchecked")
    public Operator getOperatorByLocation(@PathVariable("locationId") ObjectId locationId) {
        Location location = (Location) locationRepository.read(locationId);
        Operator operator = location.getOperator();
        return operator;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Operator modifyOperatorById(@PathVariable("locationId") ObjectId locationId,
                                     @Valid @RequestBody Operator operator) {
        Location location = (Location) locationRepository.read(locationId);
        try {
            Operator old = (Operator) repository.read(location.getOperator().get_id());
            operator.set_id(old.get_id());
            return repository.update(operator);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public Operator createOperator(@PathVariable("locationId") ObjectId locationId,
                                 @Valid @RequestBody Operator operator) {
        Key<Operator> key = repository.create(operator);
        ObjectId id = new ObjectId(key.getId().toString());
        Location location = (Location) locationRepository.read(locationId);
        location.setOperator(operator);
        locationRepository.update(location);
        return (Operator) repository.read(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public WriteResult deleteOperator(@PathVariable("locationId") ObjectId locationId) {
        Location location = (Location) locationRepository.read(locationId);
        Operator operator = location.getOperator();
        UpdateOperations<Location> update = locationRepository.getDatastore().createUpdateOperations(Location.class).unset("operator");
        locationRepository.getDatastore().update(location, update);
        return repository.delete(operator);
    }
}
