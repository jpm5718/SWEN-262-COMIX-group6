package src.model.collections.modifyComicType;

import src.model.comics.AuthenticatedComic;
import src.model.comics.Comic;

public class AuthenticateStrategy implements DecoratorStrategy {
    @Override
    public Comic decorate(Comic comic) {
        comic = new AuthenticatedComic(comic);
        return comic;
    }
}
