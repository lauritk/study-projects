package fi.jyu.soa.rest.security.demo4.models;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Score {

    private String attribute; // cleanliness
    private Integer value; //0-10

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
