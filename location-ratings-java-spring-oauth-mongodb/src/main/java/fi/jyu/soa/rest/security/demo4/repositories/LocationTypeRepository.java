package fi.jyu.soa.rest.security.demo4.repositories;

import fi.jyu.soa.rest.security.demo4.models.LocationType;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

@Repository
public class LocationTypeRepository extends BaseRepository {

    public LocationTypeRepository() { super(LocationType.class); }

    public LocationType update(LocationType locationType) {

        //TODO

        UpdateOperations<LocationType> update = super.createOperations();

        UpdateResults updateResults = super.update(locationType, update);
        return (LocationType) super.read(locationType.get_id());
    }

}