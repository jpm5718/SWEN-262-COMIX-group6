/**
 * @author Dan Corcoran
 */


package src.model.comics;

public interface Comic {
    String getSeries();
    String getIssue();
    String getFullTitle();
    String getVarDesc();
    String getPublisher();
    String getReleaseDate();
    String getFormat();
    String getDateAdded();
    String getCreators();
    double getValue();
    int getId();
}
