package src.model.collections.sort;

import src.model.comics.Comic;
import java.util.Map;

public interface CollectionSortStrategy {
    Map<Integer, Comic> Sort();
}
