package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.comics.Comic;

public class RemoveComic implements Command {

    private PersonalCollection collection;
    private Comic comic;

    public RemoveComic(Comic comic, PersonalCollection collection) {       
        this.collection = collection;
        this.comic = comic;
    }

    @Override
    public void execute() {
        collection.removeComic(comic);
    }

    @Override
    public void undo() {
        collection.addComic(comic);
    }

    @Override
    public void redo() {
        execute();
    }
}
