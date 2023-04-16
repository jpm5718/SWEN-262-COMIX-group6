/**
 * @author Dan Corcoran
 */

package src.model.collections.editComic;

import src.model.comics.Comic;
import src.model.comics.Publisher;

public class EditPublisher implements EditStrategy {

    @Override
    public Comic edit(Comic comic, String newAttribute) {
        comic.setPublisher(new Publisher(newAttribute));
        return comic;
    }
}
