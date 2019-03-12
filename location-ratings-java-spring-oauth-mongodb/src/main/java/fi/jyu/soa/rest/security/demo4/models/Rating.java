package fi.jyu.soa.rest.security.demo4.models;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

@Embedded
public class Rating {

    @Embedded
    private List<Score> scores;
    private String comment;

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addScore(Score score) { this.scores.add(score); }
}
