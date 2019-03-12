package fi.jyu.soa.rest.security.demo4.repositories;

import fi.jyu.soa.rest.security.demo4.models.Coordinate;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

@Repository
public class CoordinateRepository extends BaseRepository {

    public CoordinateRepository() { super(Coordinate.class); }

    public Coordinate update(Coordinate coordinate) {

        //TODO

        UpdateOperations<Coordinate> update = super.createOperations();

        UpdateResults updateResults = super.update(coordinate, update);
        return (Coordinate) super.read(coordinate.get_id());
    }

}