package fi.jyu.soa.rest.security.demo4.models;

import org.mongodb.morphia.annotations.Entity;

import java.net.URL;

@Entity(value = "images", noClassnameStored = true)
public class Image extends BaseModel {

    private URL imageUrl;
    private String name;

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
