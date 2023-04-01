package src.model.command;

import src.model.comics.Comic;

public abstract class DecoratorCommand implements Command {

    protected Comic comic;
    protected Comic decoratedComic;

    public DecoratorCommand(Comic comic) {
        this.comic = comic;

        }

        @Override
    public final void execute() {
        onExecute();
    }

    @Override
    public final void undo() {
        onUndo();
    }

    @Override
    public final void redo() {
        onRedo();
        }

    protected abstract void onExecute();
    protected abstract void onUndo();
    protected abstract void onRedo();
}
