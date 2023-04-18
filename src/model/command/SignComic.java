package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.collections.modifyComicType.SignStrategy;
import src.model.comics.Comic;

public class SignComic extends DecoratorCommand {
    
    public SignComic(Comic comic, PersonalCollection personalCollection) {
        super(comic, personalCollection);
    }

    @Override
    protected void onExecute() {
        System.out.println(personalCollection);
        personalCollection.setDecoratorStrategy(new SignStrategy());
        decoratedComic = personalCollection.decorateComic(comic);
    }
}
