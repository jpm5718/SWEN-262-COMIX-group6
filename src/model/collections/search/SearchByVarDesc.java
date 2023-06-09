/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;

public class SearchByVarDesc implements SearchStrategy {
    @Override
    public ArrayList<Comic> search(ArrayList<Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        if (exactMatch) {
            for (Comic comic : collection) {
                if (comic.getVarDesc().toLowerCase().equals(term.toLowerCase())) {
                    results.add(comic);
                }
            }
        } else {
            for (Comic comic : collection) {
                if (comic.getVarDesc().toLowerCase().contains(term.toLowerCase())) {
                    results.add(comic);
                }
            }
        }

        return results;
    }
}
