/**
 * @author Dan Corcoran
 */


package src.model.collections.sort;

import src.model.comics.Comic;

import java.util.ArrayList;

public interface SortStrategy {
    ArrayList<Comic> sort(ArrayList<Comic> comics);
}
