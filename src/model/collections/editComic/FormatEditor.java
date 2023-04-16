/**
 * @author Dan Corcoran
 */

package src.model.collections.editComic;

import src.model.comics.Comic;

public class FormatEditor implements EditStrategy {

    @Override
    public Comic edit(Comic comic, String newAttribute) {
        comic.setFormat(newAttribute);
        return comic;
    }
}
