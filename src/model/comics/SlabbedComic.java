package src.model.comics;

public class SlabbedComic extends ComicBookDecorator{


    public SlabbedComic(Comic comic) {
        super(comic);
    }

    @Override
    public double getValue() {
        return comic.getValue() * 2;
    }
}
