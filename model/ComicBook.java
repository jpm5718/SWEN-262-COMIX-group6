package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ComicBook implements Comic {

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

    public ComicBook() {}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
