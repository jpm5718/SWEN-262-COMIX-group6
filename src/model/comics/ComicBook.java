/**
 * @author Dan Corcoran
 */


package src.model.comics;

import java.util.Objects;
import java.util.Queue;

public class ComicBook implements Comic {

    private String series;
    private String issue;
    private String fullTitle;
    private String varDesc;
    private String publisher;
    private String releaseDate;
    private String format;
    private String dateAdded;
    private String creators;
    private double value;
    private final int id;

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
        id = Integer.parseInt(Objects.requireNonNull(attributes.poll()));
        value = 9.99; //base value of comic (assumption)
    }

    public String getSeries() { return series; }

    public String getIssue() { return issue; }

    public String getFullTitle() { return fullTitle; }

    public String getVarDesc() { return varDesc; }

    public String getPublisher() { return publisher; }

    public String getReleaseDate() { return releaseDate; }

    public String getFormat() { return format; }

    public String getDateAdded() { return dateAdded; }

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
