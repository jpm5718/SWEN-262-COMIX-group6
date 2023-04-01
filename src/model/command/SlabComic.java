package src.model.command;

import src.model.comics.GradedComic;
import src.model.comics.SlabbedComic;

public class SlabComic extends DecoratorCommand {

    public SlabComic(GradedComic comic) {
        super(comic);
    }

    @Override
    protected void onExecute() {
        decoratedComic = new SlabbedComic(comic);
    }

    @Override
    protected void onUndo() {
        // Undo will automatically work because of DecoratorCommand's undo method        
    }

    @Override
    protected void onRedo() {
        decoratedComic = new SlabbedComic(comic);
    }
}