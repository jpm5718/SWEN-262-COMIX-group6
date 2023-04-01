package src.model.collections.search;

import src.model.comics.Comic;

public interface CollectionSearchStrategy {
    Comic search(boolean exactMatch);
}
