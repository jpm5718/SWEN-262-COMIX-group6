/**
 * @author Dan Corcoran
 */


package src.model.collections.sort;

import src.model.comics.Comic;
import java.util.Map;

public interface SortStrategy {
    Map<Integer, Comic> sort(Map<Integer, Comic> collection);
}
