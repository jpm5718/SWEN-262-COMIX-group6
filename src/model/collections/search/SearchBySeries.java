/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Map;

public class SearchBySeries implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(Map<Integer, Comic> collection, String term, boolean exactMatch) {
        return null;
    }
}
