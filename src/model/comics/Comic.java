/**
 * @author Dan Corcoran
 */


package src.model.comics;

public interface Comic {
    String getSeries();
    String getIssue();
    String getTitle();
    String getVarDesc();
    Publisher getPublisher();
    String getReleaseDate();
    String getFormat();
    String getDateAdded();
    Creators getCreators();
    double getValue();
    int getId();
}
