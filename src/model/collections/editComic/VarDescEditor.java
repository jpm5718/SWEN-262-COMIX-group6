/**
 * @author Dan Corcoran
 */

package src.model.collections.editComic;

import src.model.comics.Comic;

public class VarDescEditor implements EditStrategy {

    @Override
    public Comic edit(Comic comic, String newAttribute) {
        comic.setVarDesc(newAttribute);
        return comic;
    }
}
