package src.model.command;

import src.model.comics.AuthenticatedComic;
import src.model.comics.SignedComic;

public class AuthenticateComic extends DecoratorCommand {

    public AuthenticateComic(SignedComic signedComic) {
        super(signedComic);
    }

    @Override
    protected void onExecute() {
        decoratedComic = new AuthenticatedComic(comic);
    }

    @Override
    protected void onUndo() {
    }

    @Override
    protected void onRedo() {
        decoratedComic = new AuthenticatedComic(comic);
    }
}