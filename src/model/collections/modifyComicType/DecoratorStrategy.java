package src.model.collections.modifyComicType;

import src.model.comics.Comic;

public interface DecoratorStrategy {
    Comic decorate(Comic comic);
}
