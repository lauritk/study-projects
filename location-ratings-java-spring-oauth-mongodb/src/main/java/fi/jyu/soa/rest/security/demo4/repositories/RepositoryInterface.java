package fi.jyu.soa.rest.security.demo4.repositories;

import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.util.List;
import java.util.Map;

public interface RepositoryInterface<T> {

    public Key<T> create(T entity);

    public T read(ObjectId id);

    public UpdateResults update(T entity, UpdateOperations<T> operations);

    public WriteResult delete(T entity);

    public UpdateOperations<T> createOperations();

    public List<T> getCollection(Map<String, List<String>> filter);

    public Datastore getDatastore();
}