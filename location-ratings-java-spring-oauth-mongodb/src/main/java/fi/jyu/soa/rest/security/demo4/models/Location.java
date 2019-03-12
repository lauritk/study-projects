package fi.jyu.soa.rest.security.demo4.models;

import com.mongodb.DBRef;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

@Entity(value = "locations", noClassnameStored = true)
public class Location extends BaseModel {

    private String name;
    @Reference
    private LocationType locationType;
    private String slug;
    @Reference
    private Coordinate coordinate;
    @Reference
    private Operator operator;
    @Reference
    private Address address;
    @Embedded
    private List<Rating> ratings;
    @Embedded
    private List<Image> images;
    private List<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Image> getImages() {
        return images;
    }

    public void addImage(Image image) { this.images.add(image); }

    public void addRating(Rating rating) { this.ratings.add(rating); }

    public void addTag(String tag) { this.tags.add(tag); }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
