/**
 * @author Dan Corcoran
 */


package src.model.comics;

public class AuthenticatedComic extends ComicBookDecorator {

    public AuthenticatedComic(Comic comic) {
        super(comic);
    }

    @Override
    public double getValue() {
        return comic.getValue() * 1.2;
    }
}
