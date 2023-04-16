/**
 * @author Dan Corcoran
 */

package src.model.collections.modifyComicType;

import src.model.comics.Comic;
import src.model.comics.SlabbedComic;

public class SlabStrategy implements DecoratorStrategy {
    @Override
    public Comic decorate(Comic comic) {
        comic = new SlabbedComic(comic);
        return comic;
    }
}
