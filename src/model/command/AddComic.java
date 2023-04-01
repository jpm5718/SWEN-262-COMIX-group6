package src.model.command;

import java.util.List;

import src.model.comics.Comic;

public class AddComic implements Command {
    private Comic comic;
    private List<Comic> personalCollection;

    public AddComic(Comic comic, List<Comic> personalCollection) {
        this.comic = comic;
        this.personalCollection = personalCollection;
    }

    @Override
    public void execute() {
        personalCollection.add(comic);
    }

    @Override
    public void undo() {
        personalCollection.remove(comic);
    }

    @Override
    public void redo() {
        execute();
    }
}