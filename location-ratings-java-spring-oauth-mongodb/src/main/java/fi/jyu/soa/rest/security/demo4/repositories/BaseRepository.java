package fi.jyu.soa.rest.security.demo4.repositories;


import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


public class BaseRepository<T> implements RepositoryInterface<T> {

    @Autowired
    private Datastore datastore;

    private Class<T> entity;

    public BaseRepository(Class<T> entity) {
        this.entity = entity;
    }

    @Override
    public Key<T> create(T entity) {
        return datastore.save(entity);
    }

    @Override
    public T read(ObjectId id) {
        return datastore.get(entity, id);
    }

    @Override
    public UpdateResults update(T entity, UpdateOperations<T> operations) {
        return datastore.update(entity, operations);
    }

    @Override
    public WriteResult delete(T entity) {
        return datastore.delete(entity);
    }

    @Override
    public UpdateOperations<T> createOperations() {
        return datastore.createUpdateOperations(entity);
    }

    @Override
    public List<T> getCollection(Map<String, List<String>> filter) {
        if (filter.keySet().size() < 1 ) {
            return this.datastore.find(entity).asList();
        }
        Query<T> query = this.datastore.createQuery(entity).disableValidation();
        for (String key : filter.keySet()) {
            System.out.println(key);
            System.out.println(filter.get(key));
            for (String value : filter.get(key)) {
                System.out.println(value);
                System.out.println(key);
                query.filter(key, value);
            }
        }
        return query.asList();
    }

    @Override
    public Datastore getDatastore() {
        return datastore;
    }
}