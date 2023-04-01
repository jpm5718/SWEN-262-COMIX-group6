package src.model.command;

import src.model.comics.Comic;
import src.model.comics.SignedComic;

public class SignComic extends DecoratorCommand {

    public SignComic(Comic comic) {
        super(comic);
    }

    @Override
    protected void onExecute() {
        decoratedComic = new SignedComic(comic);
    }

    @Override
    protected void onUndo() {
        // Undo will automatically work because of DecoratorCommand's undo method        
    }

    @Override
    protected void onRedo() {
        decoratedComic = new SignedComic(comic);
    }
}
