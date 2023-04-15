package src.model.collections;
import java.util.ArrayList;

import src.model.comics.Comic;
import src.model.comics.ComicBook;

public class Collection {
    ArrayList<Comic> comicColleciton;
    public Collection() {
        comicColleciton = new ArrayList<Comic>();
    }
    public void addComic(Comic comic) {
        this.comicColleciton.add(comic);
    }
    public void removeComic(Comic comic) {
        this.comicColleciton.remove(comic);
    }
    public void setNewCollection(ArrayList<Comic> newComics) {
        this.comicColleciton = newComics;
    }

}
