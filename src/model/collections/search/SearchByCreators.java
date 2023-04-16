/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Map;


public class SearchByCreators implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(Map<Integer, Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        if (exactMatch) {
            for (Comic comic : collection.values()) {
                for (String creator : comic.getCreators()) {
                    if (creator.equals(term)) {
                        results.add(comic);
                    }
                }
            }
        } else {
            for (Comic comic : collection.values()) {
                for (String creator : comic.getCreators()) {
                    if (creator.contains(term)) {
                        results.add(comic);
                    }
                }
            }
        }

        return results;
    }
}
