package fi.jyu.soa.rest.security.demo4.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("base")
public class BaseModel {

    @Id
    private ObjectId _id;
    // Using ObjectId as id for MongoDB makes everything lot easier. String format can be acquire via separate get method.

    public BaseModel() {
        this._id = new ObjectId();
    }

    @JsonIgnore
    public ObjectId get_id() {
        return _id;
    }

    public String get_idAsString() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void set_idAsString(String _id) {
        this._id = new ObjectId(_id);
    }
}
