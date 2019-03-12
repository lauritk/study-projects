package fi.jyu.soa.rest.security.demo4.models;


import org.mongodb.morphia.annotations.Entity;

@Entity(value = "coordinates", noClassnameStored = true)
public class Coordinate extends BaseModel {

    private double latitude;
    private double longitude;
    private String url;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
