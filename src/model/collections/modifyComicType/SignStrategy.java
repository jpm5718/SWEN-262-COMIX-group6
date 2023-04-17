/**
 * @author Dan Corcoran
 */

package src.model.collections.modifyComicType;

import src.model.comics.Comic;
import src.model.comics.SignedComic;

public class SignStrategy implements DecoratorStrategy {
    @Override
    public Comic decorate(Comic comic) {
        comic = new SignedComic(comic);
        return comic;
    }
}
