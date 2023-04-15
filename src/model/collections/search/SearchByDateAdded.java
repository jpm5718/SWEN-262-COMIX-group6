/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Map;

public class SearchByDateAdded implements SearchStrategy {
    @Override
    public void search(Map<Integer, Comic> collection, String term, boolean exactMatch) {
        if (exactMatch) {
            for (Comic comic : collection.values()) {
                if (comic.getDateAdded().equals(term)) {
                    System.out.println(comic.getId() + ":\t" + comic.getTitle() + "\n");
                }
            }
        } else {
            for (Comic comic : collection.values()) {
                if (comic.getDateAdded().contains(term)) {
                    System.out.println(comic.getId() + ":\t" + comic.getTitle() + "\n");
                }
            }
        }
    }
}
