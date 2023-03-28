package src.model;

import java.util.ArrayList;

public class ComicBook {
    String series;
    String issue;
    String title;
    String var_desc;
    String publisher;
    String release_date;
    String format;
    String added_date;
    String creators;

    public ComicBook() {
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAdded_date() {
        return added_date;
    }

    public void setAdded_date(String added_date) {
        this.added_date = added_date;
    }

    public String getCreators() {
        return creators;
    }

    public void setCreators(String creators) {
        this.creators = creators;
    }

    public void parseData(ArrayList<String> fields, ComicBook comic) {
        if (fields.size() != 9)
            System.out.println("NOT SIZE 9");
        else {
            comic.setSeries(fields.get(0));
            comic.setIssue(fields.get(1));
            comic.setTitle(fields.get(2));
            comic.setVar_desc(fields.get(3));
            comic.setPublisher(fields.get(4));
            comic.setRelease_date(fields.get(5));
            comic.setFormat(fields.get(6));
            comic.setAdded_date(fields.get(7));
            comic.setCreators(fields.get(8));
        }
    }
}
