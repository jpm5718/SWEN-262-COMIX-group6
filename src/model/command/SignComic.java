package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.collections.modifyComicType.SignStrategy;
import src.model.comics.Comic;

/**
 * The SignComic class extends DecoratorCommand to sign a Comic.
 */
public class SignComic extends DecoratorCommand {
    
    /**
     * Constructor for SignComic, takes in a Comic and a PersonalCollection.
     * @param comic The Comic to be signed.
     * @param personalCollection The PersonalCollection to which the Comic belongs.
     */
    public SignComic(Comic comic, PersonalCollection personalCollection) {
        super(comic, personalCollection);
    }

    /**
     * Executes the command to sign the Comic.
     */
    @Override
    protected void onExecute() {
        System.out.println(personalCollection);
        personalCollection.setDecoratorStrategy(new SignStrategy());
        decoratedComic = personalCollection.decorateComic(comic);
    }
}
