package fi.jyu.soa.rest.security.demo4.controllers;

import com.mongodb.WriteResult;
import fi.jyu.soa.rest.security.demo4.models.Address;
import fi.jyu.soa.rest.security.demo4.models.Location;
import fi.jyu.soa.rest.security.demo4.repositories.AddressRepository;
import fi.jyu.soa.rest.security.demo4.repositories.LocationRepository;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.validation.Valid;

@RequestMapping("locations/{locationId}/address")
@RestController
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping
    @SuppressWarnings("unchecked")
    public Address getAddressByLocation(@PathVariable("locationId") ObjectId locationId) {
        Location location = (Location) locationRepository.read(locationId);
        Address address = location.getAddress();
        return address;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Address modifyAddressById(@PathVariable("locationId") ObjectId locationId,
                                @Valid @RequestBody Address address) {
        Location location = (Location) locationRepository.read(locationId);
        try {
            Address old = (Address) repository.read(location.getAddress().get_id());
            address.set_id(old.get_id());
            return repository.update(address);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public Address createAddress(@PathVariable("locationId") ObjectId locationId,
                                 @Valid @RequestBody Address address) {
        Key<Address> key = repository.create(address);
        ObjectId id = new ObjectId(key.getId().toString());
        Location location = (Location) locationRepository.read(locationId);
        location.setAddress(address);
        locationRepository.update(location);
        return (Address) repository.read(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SuppressWarnings("unchecked")
    public WriteResult deleteAddress(@PathVariable("locationId") ObjectId locationId) {
        Location location = (Location) locationRepository.read(locationId);
        Address address = location.getAddress();
        UpdateOperations<Location> update = locationRepository.getDatastore().createUpdateOperations(Location.class).unset("address");
        locationRepository.getDatastore().update(location, update);
        return locationRepository.delete(address);
    }
}