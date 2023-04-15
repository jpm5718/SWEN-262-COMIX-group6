/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.Map;

public interface SearchStrategy {
    void search(Map<Integer, Comic> collection, String term, boolean exactMatch);
}
