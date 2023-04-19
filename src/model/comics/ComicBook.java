/**
 * @author Dan Corcoran
 */


package src.model.comics;

import java.util.Objects;
import java.util.Queue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComicBook implements Comic {

    @JsonProperty("series") private String series;
    @JsonProperty("issue") private String issue;
    @JsonProperty("title") private String title;
    @JsonProperty("varDesc") private String varDesc;
    @JsonProperty("releaseDate") private String releaseDate;
    @JsonProperty("format") private String format;
    @JsonProperty("dateAdded") private String dateAdded;
    @JsonProperty("publisher") private Publisher publisher;
    @JsonProperty("creators") private Creators creators;
    @JsonProperty("value") private double value;
    @JsonProperty("id") private int id;

    public ComicBook(Queue<String> attributes) {
        series = attributes.poll();
        issue = attributes.poll();
        title = attributes.poll();
        varDesc = attributes.poll();
        releaseDate = attributes.poll();
        format = attributes.poll();
        dateAdded = attributes.poll();
        publisher = new Publisher(attributes.poll());
        creators = new Creators(attributes.poll());
        id = Integer.parseInt(Objects.requireNonNull(attributes.poll()));
        value = 9.99; //base value of comic (assumption)
    }

    @JsonCreator
    public ComicBook(@JsonProperty("series") String series, @JsonProperty("issue") String issue, @JsonProperty("title") String title,
            @JsonProperty("varDesc") String varDesc, @JsonProperty("releaseDate") String releaseDate, @JsonProperty("format") String format,
            @JsonProperty("dateAdded") String dateAdded, @JsonProperty("publisher") Publisher publisher, @JsonProperty("creators") Creators creators,
            @JsonProperty("value") double value, @JsonProperty("id") int id) {
        this.series = series;
        this.issue = issue;
        this.title = title;
        this.varDesc = varDesc;
        this.releaseDate = releaseDate;
        this.format = format;
        this.dateAdded = dateAdded;
        this.publisher = publisher;
        this.creators = creators;
        this.value = value;
        this.id = id;
    }
        
    @Override
    public String getSeries() { return series; }

    @Override
    public String getIssue() { return issue; }

    @Override
    public String getTitle() { return title; }

    @Override
    public String getVarDesc() { return varDesc; }

    @Override
    public String getReleaseDate() { return releaseDate; }

    @Override
    public String getFormat() { return format; }

    @Override
    public String getDateAdded() { return dateAdded; }

    @Override
    public String getPublisher() { return publisher.getName(); }

    @Override
    public Creators getCreators() {
        return creators;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public void setIssue(String issue) {
        this.issue = issue;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setVarDesc(String varDesc) {
        this.varDesc = varDesc;
    }

    @Override
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }
}
