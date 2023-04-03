/**
 * @author Dan Corcoran
 */


package src.model.collections.sort;

import src.model.comics.Comic;
import java.util.Map;

public interface SortStrategy {
    void sort(Map<Integer, Comic> collection);
}
