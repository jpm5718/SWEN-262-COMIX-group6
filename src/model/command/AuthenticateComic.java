package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.collections.modifyComicType.AuthenticateStrategy;
import src.model.comics.SignedComic;

public class AuthenticateComic extends DecoratorCommand {

    public AuthenticateComic(SignedComic signedComic, PersonalCollection personalCollection) {
        super(signedComic, personalCollection);
    }

    @Override
    protected void onExecute() {
        personalCollection.setDecoratorStrategy(new AuthenticateStrategy());
        decoratedComic = personalCollection.decorateComic(comic);
    }
}