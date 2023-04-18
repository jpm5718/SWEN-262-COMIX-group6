package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.comics.Comic;

/**
 * The DecoratorCommand class implements the Command interface to decorate a Comic.
 */
public abstract class DecoratorCommand implements Command {

    protected Comic comic;
    protected Comic decoratedComic;
    protected PersonalCollection personalCollection;

    /**
     * Constructor for DecoratorCommand, takes in a Comic and a PersonalCollection.
     * @param comic The Comic to be decorated.
     * @param personalCollection The PersonalCollection to which the Comic belongs.
     */
    public DecoratorCommand(Comic comic, PersonalCollection personalCollection) {
        this.comic = comic;
        this.personalCollection = personalCollection;
    }

    public Comic getDecoratedComic() {
        return this.decoratedComic;
    }

    /**
     * Executes the command to decorate the Comic.
     */
    @Override
    public final void execute() {
        onExecute();
    }

    /**
     * Undo the command to remove the decorated Comic and add back the original Comic.
     */
    @Override
    public final void undo() {
        personalCollection.removeComic(decoratedComic);
        personalCollection.addComic(comic);
    }

    /**
     * Redo the command to remove the original Comic and add back the decorated Comic.
     */
    @Override
    public final void redo() {
        personalCollection.removeComic(comic);
        personalCollection.addComic(decoratedComic);
    }

    /**
     * Abstract execution method to be implemented by subclasses.
     */
    protected abstract void onExecute();
}
