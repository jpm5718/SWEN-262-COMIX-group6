package src.model.command;

import src.model.comics.Comic;
import src.model.comics.GradedComic;

public class GradeComic extends DecoratorCommand {

    private int grade;

    public GradeComic(Comic comic, int grade) {
        super(comic);
        this.grade = grade;
    }

    @Override
    protected void onExecute() {
        decoratedComic = new GradedComic(comic, grade);
    }

    @Override
    protected void onUndo() {
    }

    @Override
    protected void onRedo() {
        decoratedComic = new GradedComic(comic, grade);
    }
}