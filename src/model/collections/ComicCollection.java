package src.model.collections;

import src.model.collections.display.DisplayStrategy;
import src.model.collections.search.SearchStrategy;
import src.model.collections.sort.SortStrategy;
import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Map;

public interface ComicCollection {

    void addComic(Comic comic);

    void setSearchStrategy(SearchStrategy searchStrategy);

    ArrayList<Comic> search(String term, boolean exactMatch);

    void setSortStrategy(SortStrategy sortStrategy);

    void sort();

    void setDisplayStrategy(DisplayStrategy displayStrategy);

    void display();

    Map<Integer, Comic> getCollection();

    int getNumberOfIssues();
}