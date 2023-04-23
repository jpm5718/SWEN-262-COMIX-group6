/**
 * @author Dan Corcoran
 */


package src.model.comics;

public class GradedComic extends ComicBookDecorator {

    private final int grade;

    public GradedComic(Comic comic, int grade) {
        super(comic);
        this.grade = grade;
    }

    @Override
    public double getValue() {
        double value = comic.getValue();

        if (grade == 1) {
            value = value * .1;
        } else {
            value = Math.log10(grade) * value;
        }

        return value;
    }
}
