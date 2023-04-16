/**
 * @author Dan Corcoran
 */

package src.model.collections.editComic;

import src.model.comics.Comic;
import src.model.comics.Creators;

public class CreatorsEditor implements EditStrategy {

    @Override
    public Comic edit(Comic comic, String newAttribute) {
        comic.setCreators(new Creators(newAttribute));
        return comic;
    }
}
