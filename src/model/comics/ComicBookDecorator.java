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
    public Creators getCreators() {
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

    @Override
    public void setSeries(String series) {
        comic.setSeries(series);
    }

    @Override
    public void setIssue(String issue) {
        comic.setIssue(issue);
    }

    @Override
    public void setTitle(String title) {
        comic.setTitle(title);
    }

    @Override
    public void setVarDesc(String varDesc) {
        comic.setVarDesc(varDesc);
    }

    @Override
    public void setPublisher(Publisher publisher) {
        comic.setPublisher(publisher);
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        comic.setReleaseDate(releaseDate);
    }

    @Override
    public void setFormat(String format) {
        comic.setFormat(format);
    }

    @Override
    public void setDateAdded(String dateAdded) {
        comic.setDateAdded(dateAdded);
    }

    @Override
    public void setCreators(Creators creators) {
        comic.setCreators(creators);
    }

    @Override
    public void setValue(double value) {
        comic.setValue(value);
    }
}
