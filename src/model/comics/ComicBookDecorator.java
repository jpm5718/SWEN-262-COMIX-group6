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
    public String getTitle() {
        return comic.getTitle();
    }

    @Override
    public String getVarDesc() {
        return comic.getVarDesc();
    }

    @Override
    public Publisher getPublisher() {
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
    public String[] getCreators() {
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
