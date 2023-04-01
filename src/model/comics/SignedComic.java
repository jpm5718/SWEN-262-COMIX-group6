package src.model.comics;

public class SignedComic extends ComicBookDecorator {

    public SignedComic(Comic comic) {
        super(comic);
    }

    @Override
    public double getValue() {

        //is this meant to scale exponentially? Check with Abdul
        //I.E. are we adding 5% of the current value each time it's signed or 5% of the original unsigned value?
        //(a $10 comic signed 5 times could be worth either 12.76 or 12.50)
        return comic.getValue() * 1.05;
    }
}
