package fi.jyu.soa.rest.security.demo4.repositories;

import fi.jyu.soa.rest.security.demo4.models.Operator;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

@Repository
public class OperatorRepository extends BaseRepository {

    public OperatorRepository() { super(Operator.class); }

    public Key<Operator> create(Operator operator) {

        if (operator.getAddress() != null) {
            super.create(operator.getAddress());
        }

        return super.create(operator);

    }

    public Operator update(Operator operator) {

        //TODO

        UpdateOperations<Operator> update = super.createOperations();

        UpdateResults updateResults = super.update(operator, update);
        return (Operator) super.read(operator.get_id());
    }

}
