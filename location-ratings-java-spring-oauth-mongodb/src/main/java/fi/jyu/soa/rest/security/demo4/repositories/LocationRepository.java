package fi.jyu.soa.rest.security.demo4.repositories;

import fi.jyu.soa.rest.security.demo4.models.Location;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepository extends BaseRepository {

    public LocationRepository() {
        super(Location.class);
    }

    public Key<Location> create(Location location) {

        // We could also just create the 'null' properties without checking?

        if (location.getAddress() != null) {
            super.create(location.getAddress());
        }

        if (location.getOperator() != null) {
            if(location.getOperator().getAddress() != null) {
                super.create(location.getOperator().getAddress());
            }
            super.create(location.getOperator());

        }

        if (location.getCoordinate() != null) {
            super.create(location.getCoordinate());
        }

        if (location.getLocationType() != null) {
            super.create(location.getLocationType());
        }

        //TODO add create images, rating, scores

        return super.create(location);

    }

    public Location update(Location location) {
        UpdateOperations<Location> operations = super.createOperations();

        if (location.getName() != null) {
            operations.set("name", location.getName());
        }

        if (location.getLocationType() != null) {
            operations.set("locationType", location.getLocationType());
        }

        if (location.getSlug() != null) {
            operations.set("slug", location.getSlug());
        }

        if (location.getCoordinate() != null) {
            operations.set("coordinate", location.getCoordinate());
        }

        if (location.getOperator() != null) {
            operations.set("operator", location.getOperator());
        }

        // TODO support removing (setting null) the address
        if (location.getAddress() != null) {
            operations.set("address", location.getAddress());
        }

        if (location.getRatings() != null) {
            operations.set("ratings", location.getRatings());
        }

        if (location.getImages() != null) {
            operations.set("images", location.getImages());
        }

        if (location.getTags() != null) {
            operations.set("tags", location.getTags());
        }

        UpdateResults results = super.update(location, operations);

        return (Location) super.read(location.get_id());
    }

    public Location updateDeletion(Location location) {
        //TODO update location references when deleted something
        return null;
    }
}
