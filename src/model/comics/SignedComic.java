/**
 * @author Dan Corcoran
 */


package src.model.comics;

public class SignedComic extends ComicBookDecorator {

    public SignedComic(Comic comic) {
        super(comic);
    }

    @Override
    public double getValue() {
        return comic.getValue() * 1.05;
    }
}
