package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.comics.Comic;

public abstract class DecoratorCommand implements Command {

    protected Comic comic;
    protected Comic decoratedComic;
    protected PersonalCollection personalCollection;

    public DecoratorCommand(Comic comic, PersonalCollection personalCollection) {
        this.comic = comic;
        this.personalCollection = personalCollection;
    }

    public Comic getDecoratedComic() {
        return this.decoratedComic;
    }

    @Override
    public final void execute() {
        onExecute();
    }

    @Override
    public final void undo() {
        personalCollection.removeComic(decoratedComic);
        personalCollection.addComic(comic);
    }

    @Override
    public final void redo() {
        personalCollection.removeComic(comic);
        personalCollection.addComic(decoratedComic);
    }

    protected abstract void onExecute();
}
