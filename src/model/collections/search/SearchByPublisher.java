/**
 * @author Dan Corcoran
 */

package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Map;

public class SearchByPublisher implements SearchStrategy {
    @Override
    public ArrayList<Comic> search(Map<Integer, Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        if (exactMatch) {
            for (Comic comic : collection.values()) {
                if (comic.getPublisher().equals(term)) {
                    results.add(comic);
                }
            }
        } else {
            for (Comic comic : collection.values()) {
                if (comic.getPublisher().contains(term)) {
                    results.add(comic);
                }
            }
        }

        return results;
    }
}
