package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.collections.modifyComicType.SlabStrategy;
import src.model.comics.GradedComic;

/**
 * The SlabComic class extends DecoratorCommand to slab a GradedComic.
 */
public class SlabComic extends DecoratorCommand {

    /**
     * Constructor for SlabComic, takes in a GradedComic and a PersonalCollection.
     * @param comic The GradedComic to be slabbed.
     * @param personalCollection The PersonalCollection to which the GradedComic belongs.
     */
    public SlabComic(GradedComic comic, PersonalCollection personalCollection) {
        super(comic, personalCollection);
    }

     /**
     * Executes the command to slab the GradedComic.
     */
    @Override
    protected void onExecute() {
        personalCollection.setDecoratorStrategy(new SlabStrategy());
        decoratedComic = personalCollection.decorateComic(comic.getId());
    }
}