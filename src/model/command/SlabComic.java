package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.collections.modifyComicType.SlabStrategy;
import src.model.comics.GradedComic;

public class SlabComic extends DecoratorCommand {

    public SlabComic(GradedComic comic, PersonalCollection personalCollection) {
        super(comic, personalCollection);
    }

    @Override
    protected void onExecute() {
        personalCollection.setDecoratorStrategy(new SlabStrategy());
        decoratedComic = personalCollection.decorateComic(comic);
    }
}