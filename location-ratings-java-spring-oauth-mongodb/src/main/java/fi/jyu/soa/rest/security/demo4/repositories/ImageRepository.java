package fi.jyu.soa.rest.security.demo4.repositories;

import fi.jyu.soa.rest.security.demo4.models.Image;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepository extends BaseRepository {

    public ImageRepository() { super(Image.class); }

    //TODO

}