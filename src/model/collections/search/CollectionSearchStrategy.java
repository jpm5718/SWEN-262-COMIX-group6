/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;

public interface CollectionSearchStrategy {
    ArrayList<Comic> search(String term, boolean exactMatch);
}
