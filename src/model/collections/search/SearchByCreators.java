/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;
import java.util.ArrayList;


public class SearchByCreators implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(ArrayList<Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        if (exactMatch) {
            for (Comic comic : collection) {
                String creators = comic.getCreators().toLowerCase();
                if (creators.equals(term.toLowerCase() + " | ")) {
                    results.add(comic);
                } 
            }
        } else {
            for (Comic comic : collection) {
                String creators = comic.getCreators().toLowerCase();
                if (creators.contains(term.toLowerCase())) {
                    results.add(comic);
                }
            }
        }

        return results;
    }
}
