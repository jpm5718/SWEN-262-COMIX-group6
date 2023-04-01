package src.model.command;

import java.util.List;

import src.model.comics.Comic;

public class RemoveComic implements Command {

    private List<Comic> collection;
    private Comic comic;
    private int index;

    public RemoveComic(List<Comic> collection, Comic comic) {       
        this.collection = collection;
        this.comic = comic;
    }

    @Override
    public void execute() {
        index = collection.indexOf(comic);
        collection.remove(comic);
    }

    @Override
    public void undo() {
        collection.add(index, comic);
    }

    @Override
    public void redo() {
        execute();
    }
}
