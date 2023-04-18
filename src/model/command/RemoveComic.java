package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.comics.Comic;

/**
 * The RemoveComic class implements the Command interface to remove a Comic from a PersonalCollection.
 */
public class RemoveComic implements Command {

    private PersonalCollection collection;
    private Comic comic;

    /**
     * Constructor for RemoveComic, takes in a Comic and a PersonalCollection.
     * @param comic The Comic to be removed.
     * @param collection The PersonalCollection from which the Comic is being removed.
     */
    public RemoveComic(Comic comic, PersonalCollection collection) {       
        this.collection = collection;
        this.comic = comic;
    }

    /**
     * Executes the command to remove the Comic from the PersonalCollection.
     */
    @Override
    public void execute() {
        collection.removeComic(comic);
    }

    /**
     * Undo method to add the Comic back to the PersonalCollection.
     */
    @Override
    public void undo() {
        collection.addComic(comic);
    }

    /**
     * Redo method to execute the command again.
     */
    @Override
    public void redo() {
        execute();
    }
}
