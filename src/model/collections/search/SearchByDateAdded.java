/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;

public class SearchByDateAdded implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(ArrayList<Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        if (exactMatch) {
            for (Comic comic : collection) {
                if (comic.getDateAdded().equals(term)) {
                    results.add(comic);
                }
            }
        } else {
            for (Comic comic : collection) {
                if (comic.getDateAdded().contains(term)) {
                    results.add(comic);
                }
            }
        }

        return results;
    }
}
