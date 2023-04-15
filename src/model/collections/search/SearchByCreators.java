/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.Map;

public class SearchByCreators implements SearchStrategy {

    @Override
    public void search(Map<Integer, Comic> collection, String term, boolean exactMatch) {
        return null;
    }
}
