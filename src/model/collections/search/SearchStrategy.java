/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Map;

public interface SearchStrategy {
    ArrayList<Comic> search(Map<Integer, Comic> collection, String term, boolean exactMatch);
}
