package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.comics.Comic;

public class GradeComic extends DecoratorCommand {

    private int grade;

    public GradeComic(Comic comic, int grade, PersonalCollection personalCollection) {
        super(comic, personalCollection);
    }

    @Override
    protected void onExecute() {
        decoratedComic = personalCollection.gradeComic(comic, grade);
    }
}