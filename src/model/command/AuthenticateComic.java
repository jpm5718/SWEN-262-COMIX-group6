package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.collections.modifyComicType.AuthenticateStrategy;
import src.model.comics.SignedComic;

/**
 * The AuthenticateComic class extends DecoratorCommand to authenticate a SignedComic.
 */
public class AuthenticateComic extends DecoratorCommand {

    /**
     * Constructor for AuthenticateComic, takes in a SignedComic and a PersonalCollection.
     * @param signedComic The SignedComic to be authenticated.
     * @param personalCollection The PersonalCollection to which the SignedComic belongs.
     */
    public AuthenticateComic(SignedComic signedComic, PersonalCollection personalCollection) {
        super(signedComic, personalCollection);
    }

    /**
     * Executes the command to authenticate the SignedComic.
     */
    @Override
    protected void onExecute() {
        personalCollection.setDecoratorStrategy(new AuthenticateStrategy());
        decoratedComic = personalCollection.decorateComic(comic.getId());
    }
}