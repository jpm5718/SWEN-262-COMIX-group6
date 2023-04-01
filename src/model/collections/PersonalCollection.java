package src.model.collections;

import src.model.collections.display.DisplayStrategy;
import src.model.collections.search.CollectionSearchStrategy;
import src.model.collections.search.SearchByTitle;
import src.model.collections.sort.CollectionSortStrategy;
import src.model.comics.Comic;
import src.model.comics.ComicBook;
import src.model.comics.GradedComic;

import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class PersonalCollection {

    Map<Integer, Comic> collection;
    private String name;
    private CollectionSearchStrategy searchStrategy;
    private CollectionSortStrategy sortStrategy;
    private DisplayStrategy displayStrategy;
    private int numberOfIssues;

    public PersonalCollection(String name) {
        collection = new TreeMap<>();
        this.name = name;
        searchStrategy = new SearchByTitle();
        sortStrategy = null;
        numberOfIssues = 0;
    }

    public void addComic(Comic comic) {
        collection.put(comic.getId(), comic);
        numberOfIssues++;
    }

    public void addComic(Queue<String> attributes) {
        Comic comic = new ComicBook(attributes);
        collection.put(comic.getId(), comic);
        numberOfIssues++;
    }

    public void removeComic(Comic comic) {
        collection.remove(comic.getId());
        numberOfIssues--;
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

    public double getValue() {
        double value = 0;

        for (Map.Entry<Integer, Comic> comicEntry : collection.entrySet()) {

            //how's that for a weird looking call. comicEntry.getValue() gets the comic
            //comicEntry.getValue().getValue() gets the value of the comic
            value = value + comicEntry.getValue().getValue();
        }

        return value;
    }
}