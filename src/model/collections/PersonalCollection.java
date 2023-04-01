package src.model.collections;

import src.model.collections.display.DisplayStrategy;
import src.model.collections.search.CollectionSearchStrategy;
import src.model.collections.search.SearchByTitle;
import src.model.collections.sort.CollectionSortStrategy;
import src.model.comics.Comic;
import src.model.comics.GradedComic;

import java.util.Map;
import java.util.TreeMap;

public class PersonalCollection {

    Map<Integer, Comic> collection;
    private String name;
    private CollectionSearchStrategy searchStrategy;
    private CollectionSortStrategy sortStrategy;
    private DisplayStrategy displayStrategy;
    private int numberOfIssues;
    private int value;

    public PersonalCollection(String name) {
        collection = new TreeMap<>();
        this.name = name;
        searchStrategy = new SearchByTitle();
        sortStrategy = null;
        numberOfIssues = 0;
        value = 0;
    }

    public void addComic(Comic comic) {
        collection.put(comic.getId(), comic);
        numberOfIssues++;
        value += comic.getValue();
    }

    public void removeComic(Comic comic) {
        collection.remove(comic.getId());
        numberOfIssues--;
        value -= comic.getValue();
    }

    public Comic getComic() {
        return null;
    }

    public void gradeComic(Comic comic, int grade) {
        comic = new GradedComic(comic, grade);
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Comic> getCollection() {
        return collection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSearchStrategy(CollectionSearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public Comic executeSearch(boolean exactMatch) {
        return searchStrategy.search(exactMatch);
    }

    public void setSortStrategy(CollectionSortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void executeSort() {
        this.collection = sortStrategy.Sort();
    }

    public void setDisplayStrategy(DisplayStrategy displayStrategy) {
        this.displayStrategy = displayStrategy;
    }

    public void display() {
        displayStrategy.display();
    }

    public int getNumberOfIssues() {
        return numberOfIssues;
    }

    public int getValue() {
        return value;
    }
}
