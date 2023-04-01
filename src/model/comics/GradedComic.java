package src.model.comics;

public class GradedComic extends ComicBookDecorator {

    private final int grade;

    public GradedComic(Comic comic, int grade) {
        super(comic);
        this.grade = grade;
    }

    @Override
    public double getValue() {
        double value = 0;

        if (grade == 1) {
            value = comic.getValue() * .1;
        } else {
            value = Math.log10(grade) * comic.getValue();
        }

        return value;
    }
}
