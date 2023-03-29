package src.model;

/**
 * Class that represents a base comic book object.
 * 
 * @author James McGuire
 */
public class ComicBook {
    /* All data fields a comic book can have */
    private String series;
    private String issue;
    private String full_title;
    private String var_desc;
    private String publisher;
    private String release_date;
    private String format;
    private String date_added;
    private String creators;

    private double value = 9.99;

    /* Default constructor to initialize a comic book object */
    public ComicBook() {
    }

    /* Constructor to create a comic book object with parameters */
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
