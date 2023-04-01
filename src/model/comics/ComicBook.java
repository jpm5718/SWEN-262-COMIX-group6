package src.model.comics;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Queue;

public class ComicBook implements Comic {

    @JsonProperty("series") private String series;
    @JsonProperty("issue") private String issue;
    @JsonProperty("fullTitle") private String fullTitle;
    @JsonProperty("varDesc") private String varDesc;
    @JsonProperty("publisher") private String publisher;
    @JsonProperty("releaseDate") private String releaseDate;
    @JsonProperty("format") private String format;
    @JsonProperty("dateAdded") private String dateAdded;
    @JsonProperty("creators") private String creators;
    @JsonProperty("value") private double value;
    @JsonProperty("id") private final int id;

    public ComicBook(Queue<String> attributes) {
        series = attributes.poll();
        issue = attributes.poll();
        fullTitle = attributes.poll();
        varDesc = attributes.poll();
        publisher = attributes.poll();
        releaseDate = attributes.poll();
        format = attributes.poll();
        dateAdded = attributes.poll();
        creators = attributes.poll();
        value = Double.parseDouble(Objects.requireNonNull(attributes.poll()));
        id = Integer.parseInt(Objects.requireNonNull(attributes.poll()));
    }

    public String getSeries() {
        return series;
    }

    public String getIssue() {
        return issue;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public String getVarDesc() {
        return varDesc;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getFormat() {
        return format;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getCreators() {
        return creators;
    }

    public double getValue() {
        return value;
    }
    
    public int getId() {
        return id;
    }
}
