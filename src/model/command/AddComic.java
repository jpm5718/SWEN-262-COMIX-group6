package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.comics.Comic;

public class AddComic implements Command {
    private Comic comic;
    private PersonalCollection personalCollection;

    public AddComic(Comic comic, PersonalCollection personalCollection) {
        this.comic = comic;
        this.personalCollection = personalCollection;
    }

    @Override
    public void execute() {
        personalCollection.addComic(comic);
    }

    @Override
    public void undo() {
        personalCollection.removeComic(comic);
    }

    @Override
    public void redo() {
        execute();
    }
}