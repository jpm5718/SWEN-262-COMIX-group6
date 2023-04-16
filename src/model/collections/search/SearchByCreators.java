/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;
import src.model.comics.Creators;

import java.util.ArrayList;
import java.util.Map;


public class SearchByCreators implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(Map<Integer, Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        if (exactMatch) {
            for (Comic comic : collection.values()) {
                Creators creators = comic.getCreators();
                for (String creator : creators.getCreators()) {
                    if (creator.equals(term)) {
                        results.add(comic);
                    }
                }
            }
        } else {
            for (Comic comic : collection.values()) {
                Creators creators = comic.getCreators();
                for (String creator : creators.getCreators()) {
                    if (creator.contains(term)) {
                        results.add(comic);
                    }
                }
            }
        }

        return results;
    }
}
