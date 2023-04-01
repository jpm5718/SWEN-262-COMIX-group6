/**
 * @author Dan Corcoran
 */


package src.model.collections.display;

import src.model.comics.Comic;

import java.util.Map;

public interface DisplayStrategy {
    void display(Map<Integer, Comic> collection);
}
