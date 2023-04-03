/**
 * @author Dan Corcoran
 */


package src.model.comics;

public abstract class ComicBookDecorator implements Comic {

    protected Comic comic;

    public ComicBookDecorator(Comic comic) {
        this.comic = comic;
    }

    @Override
    public String getSeries() {
        return comic.getSeries();
    }

    @Override
    public String getIssue() {
        return comic.getIssue();
    }

    @Override
    public String getFullTitle() {
        return comic.getFullTitle();
    }

    @Override
    public String getVarDesc() {
        return comic.getVarDesc();
    }

    @Override
    public String getPublisher() {
        return comic.getPublisher();
    }

    @Override
    public String getReleaseDate() {
        return comic.getReleaseDate();
    }

    @Override
    public String getFormat() {
        return comic.getFormat();
    }

    @Override
    public String getDateAdded() {
        return comic.getDateAdded();
    }

    @Override
    public String getCreators() {
        return comic.getCreators();
    }

    @Override
    public double getValue() {
        return comic.getValue();
    }

    @Override
    public int getId() {
        return comic.getId();
    }
}