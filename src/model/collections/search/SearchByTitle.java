/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;

public class SearchByTitle implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(ArrayList<Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        if (exactMatch) {
            for (Comic comic : collection) {
                if (comic.getTitle().toLowerCase().equals(term.toLowerCase())) {
                    results.add(comic);
                }
            }
        } else {
            for (Comic comic : collection) {
                if (comic.getTitle().toLowerCase().contains(term.toLowerCase())) {
                    results.add(comic);
                }
            }
        }

        return results;
    }
}
