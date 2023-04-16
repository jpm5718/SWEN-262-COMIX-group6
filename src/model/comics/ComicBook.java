/**
 * @author Dan Corcoran
 */


package src.model.comics;

import java.util.Objects;
import java.util.Queue;

public class ComicBook implements Comic {

    private String series;
    private String issue;
    private String title;
    private String varDesc;
    private String releaseDate;
    private String format;
    private String dateAdded;
    private Publisher publisher;
    private Creators creators;
    private double value;
    private final int id;

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
