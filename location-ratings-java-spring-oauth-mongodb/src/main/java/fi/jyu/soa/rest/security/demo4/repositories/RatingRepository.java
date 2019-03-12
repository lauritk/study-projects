package fi.jyu.soa.rest.security.demo4.repositories;

import fi.jyu.soa.rest.security.demo4.models.Rating;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

@Repository
public class RatingRepository extends BaseRepository {

    public RatingRepository() { super(Rating.class); }

    public Rating update(Rating rating) {

        //TODO

        return null;
    }

}
