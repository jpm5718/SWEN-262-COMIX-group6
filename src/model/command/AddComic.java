package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.comics.Comic;

/**
 * The AddComic class implements the Command interface to add a comic to a PersonalCollection.
 */
public class AddComic implements Command {
    private Comic comic;
    private PersonalCollection personalCollection;

    /**
     * Constructor for AddComic, takes in a Comic and a PersonalCollection.
     * @param comic The comic to be added.
     * @param personalCollection The collection to which the comic is being added.
     */
    public AddComic(Comic comic, PersonalCollection personalCollection) {
        this.comic = comic;
        this.personalCollection = personalCollection;
    }

     /**
     * Executes the command to add the comic to the PersonalCollection.
     */
    @Override
    public void execute() {
        personalCollection.addComic(comic);
    }

    /**
     * Undo method to remove the comic from the PersonalCollection.
     */
    @Override
    public void undo() {
        personalCollection.removeComic(comic);
    }

     /**
     * Redo method to execute the command again.
     */
    @Override
    public void redo() {
        execute();
    }
}