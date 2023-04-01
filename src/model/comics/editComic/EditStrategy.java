/**
 * @author Dan Corcoran
 */


package src.model.comics.editComic;

import src.model.comics.Comic;

public interface EditStrategy {
    Comic edit(Comic comic, String newAttribute);
}
