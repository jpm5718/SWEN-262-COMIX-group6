/**
 * @author Dan Corcoran
 */

package src.model.collections.editComic;

import src.model.comics.Comic;

public class DateAddedEditor implements EditStrategy {

    @Override
    public Comic edit(Comic comic, String newAttribute) {
        comic.setDateAdded(newAttribute);
        return comic;
    }
}
