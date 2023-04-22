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
                String creators = comic.getCreators();
                String[] s = creators.split("|");
                for (String string : s) {
                    if (string.equals(term)) {
                        results.add(comic);
                    }
                }
            }
        } else {
            for (Comic comic : collection) {
                String creators = comic.getCreators();
                String[] s = creators.split("|");
                for (String string : s) {
                    if (string.contains(term)) {
                        results.add(comic);
                    }
                }
            }
        }

        return results;
    }
}
