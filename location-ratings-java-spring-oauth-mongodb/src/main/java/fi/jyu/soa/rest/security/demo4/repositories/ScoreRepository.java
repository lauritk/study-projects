package fi.jyu.soa.rest.security.demo4.repositories;

import fi.jyu.soa.rest.security.demo4.models.Score;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreRepository extends BaseRepository {

    public ScoreRepository() { super(Score.class); }

    // TODO

}
