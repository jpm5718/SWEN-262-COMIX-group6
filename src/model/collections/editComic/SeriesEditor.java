/**
 * @author Dan Corcoran
 */

package src.model.collections.editComic;

import src.model.comics.Comic;

public class SeriesEditor implements EditStrategy {
    @Override
    public Comic edit(Comic comic, String newAttribute) {
        comic.setSeries(newAttribute);
        return comic;
    }
}
