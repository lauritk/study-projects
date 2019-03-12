package fi.jyu.soa.rest.security.demo4.models;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "location_types", noClassnameStored = true)
public class LocationType extends BaseModel{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
