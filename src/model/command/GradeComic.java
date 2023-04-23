package src.model.command;

import src.model.collections.PersonalCollection;
import src.model.comics.Comic;

/**
 * The GradeComic class extends DecoratorCommand to grade a Comic.
 */
public class GradeComic extends DecoratorCommand {

    private int grade;

    /**
     * Constructor for GradeComic, takes in a Comic, a grade, and a PersonalCollection.
     * @param comic The Comic to be graded.
     * @param grade The grade to assign to the Comic.
     * @param personalCollection The PersonalCollection to which the Comic belongs.
     */
    public GradeComic(Comic comic, int grade, PersonalCollection personalCollection) {
        super(comic, personalCollection);
        this.grade = grade;
    }

    /**
     * Executes the command to grade the Comic.
     */
    @Override
    protected void onExecute() {
        decoratedComic = personalCollection.gradeComic(comic.getId(), grade);
    }
}