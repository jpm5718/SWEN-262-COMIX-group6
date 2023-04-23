/**
 * @author Dan Corcoran
 */


package src.model.comics;

public interface Comic {
    String getSeries();
    String getIssue();
    String getTitle();
    String getVarDesc();
    String getPublisher();
    String getReleaseDate();
    String getFormat();
    String getDateAdded();
    String getCreators();
    double getValue();
    int getId();
    void setSeries(String series);
    void setIssue(String issue);
    void setTitle(String title);
    void setVarDesc(String varDesc);
    void setPublisher(Publisher publisher);
    void setReleaseDate(String releaseDate);
    void setFormat(String format);
    void setDateAdded(String dateAdded);
    void setCreators(Creators creators);
    void setValue(double value);
    String toString();
}
