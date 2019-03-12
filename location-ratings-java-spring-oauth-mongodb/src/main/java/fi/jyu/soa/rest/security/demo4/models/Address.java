package fi.jyu.soa.rest.security.demo4.models;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "addresses", noClassnameStored = true)
public class Address extends BaseModel {

    private String streetAddress;
    private String postalCode;
    private String postOffice;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }
}
