package src.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents a base comic book object.
 * 
 * @author James McGuire
 */
public class ComicBook {
    /* All data fields a comic book can have as json properties */
    @JsonProperty("series") private String series;
    @JsonProperty("issue") private String issue;
    @JsonProperty("full_title") private String full_title;
    @JsonProperty("var_desc") private String var_desc;
    @JsonProperty("publisher") private String publisher;
    @JsonProperty("release_date") private String release_date;
    @JsonProperty("format") private String format;
    @JsonProperty("date_added") private String date_added;
    @JsonProperty("creators") private String creators;
    @JsonProperty("value") private double value;

    /**
     * Constructor to create a comic book object
     * @param series The series the comic book is in
     * @param issue The issue of the comic book
     * @param full_title The comic's full title
     * @param var_desc Variant description of the comic
     * @param publisher Publisher of the comic book
     * @param release_date When the comic was released
     * @param format FOrmat it is in 
     * @param date_added The date it was added to the database (?)
     * @param creators The creators of the comic book
     */
    public ComicBook(String series, String issue, String full_title, String var_desc, String publisher,
            String release_date, String format, String date_added, String creators) {
        this.series = series;
        this.issue = issue;
        this.full_title = full_title;
        this.var_desc = var_desc;
        this.publisher = publisher;
        this.release_date = release_date;
        this.format = format;
        this.date_added = date_added;
        this.creators = creators;
        this.value = 9.99; // every comic's base value is $9.99
    }

    // --------------------------------------------------------------------------------------------------------------------
    // TONS OF GETTERS AND SETTERS :0
    // --------------------------------------------------------------------------------------------------------------------
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getFull_title() {
        return full_title;
    }

    public void setFull_title(String full_title) {
        this.full_title = full_title;
    }

    public String getVar_desc() {
        return var_desc;
    }

    public void setVar_desc(String var_desc) {
        this.var_desc = var_desc;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getCreators() {
        return creators;
    }

    public void setCreators(String creators) {
        this.creators = creators;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
