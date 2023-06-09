/**
 * @author Dan Corcoran
 */

package src.model.collections.editComic;

import src.model.comics.Comic;

public class ReleaseDateEditor implements EditStrategy {

    @Override
    public Comic edit(Comic comic, String newAttribute) {
        comic.setReleaseDate(newAttribute);
        return comic;
    }
}
