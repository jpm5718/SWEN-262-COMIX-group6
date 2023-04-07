/**
 * @author Dan Corcoran
 */


package src.model.collections.editComic;

import src.model.comics.Comic;

public interface EditStrategy {
    Comic edit(Comic comic, String newAttribute);
}
