/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Map;

public class SearchByFormat implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(ArrayList<Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        if (exactMatch) {
            for (Comic comic : collection.values()) {
                if (comic.getFormat().equals(term)) {
                    results.add(comic);
                }
            }
        } else {
            for (Comic comic : collection.values()) {
                if (comic.getFormat().contains(term)) {
                    results.add(comic);
                }
            }
        }

        return results;
    }
}
