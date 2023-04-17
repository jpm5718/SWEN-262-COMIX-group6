/**
 * @author Dan Corcoran
 */

package src.model.comics;

public class SignedComic extends ComicBookDecorator {

    private double value;

    public SignedComic(Comic comic) {
        super(comic);
        value = comic.getValue() * 1.05;
    }

    @Override
    public double getValue() {
        return value;
    }
}
