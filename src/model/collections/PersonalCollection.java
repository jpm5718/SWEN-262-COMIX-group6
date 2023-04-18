/**
 * @author Dan Corcoran
 */

package src.model.collections;

import src.model.collections.modifyComicType.DecoratorStrategy;
import src.model.collections.search.SearchStrategy;
import src.model.collections.search.SearchByTitle;
import src.model.collections.sort.SortByTitle;
import src.model.collections.sort.SortStrategy;
import src.model.comics.*;
import src.model.collections.editComic.EditStrategy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class PersonalCollection implements ComicCollection {

    private Map<Integer, Comic> collection;
    private String name;
    private SearchStrategy searchStrategy;
    private SortStrategy sortStrategy;
    private EditStrategy editStrategy;
    private DecoratorStrategy decoratorStrategy;
    private int numberOfIssues;

    public PersonalCollection(String name) {
        collection = new TreeMap<>();
        this.name = name;

        //default s4trategies are searching and sorting by title
        searchStrategy = new SearchByTitle();
        sortStrategy = new SortByTitle();
        editStrategy = null;
        decoratorStrategy = null;
        numberOfIssues = 0;
    }

    /**
     * Adds a comic to the collection and increments the number of issues
     * Requires a comic object to be sent from the database of comics
     * @param comic - comic being added
     */
    @Override
    public void addComic(Comic comic) {
        if (!collection.containsKey(comic.getId())) {
            collection.put(comic.getId(), comic);
            numberOfIssues++;
        } else {
            System.out.println("Comic is already in collection\n");
        }
    }

    /**
     * Adds a comic to the collection and increments the number of issues
     * Requires a list of attributes that defines the user's comic
     * @param attributes - attributes of the comic being added
     */
    public void addComic(Queue<String> attributes) {
        Comic comic = new ComicBook(attributes);
        if (!collection.containsKey(comic.getId())) {
            collection.put(comic.getId(), comic);
            numberOfIssues++;
        } else {
            System.out.println("Comic is already in collection\n");
        }
    }

    /**
     * Removes a comic from the collection and decrements the number of issues
     * Requires a comic object to be sent from the databse of comics
     * Could be updated in the future to allow deletion based on various criteria
     * @param comic - comic being removed
     */
    public void removeComic(Comic comic) {
        collection.remove(comic.getId());
        numberOfIssues--;
    }

    /**
     * Grades a comic currently in the collection.
     * Cannot be used with decorator strategy due to the extra grade parameter
     * @param comic - comic being graded
     * @param grade - the grade applied to the comic
     */
    public void gradeComic(Comic comic, int grade) {
        try {
            int key = comic.getId();
            comic = collection.get(key);
            comic = new GradedComic(comic, grade);
            collection.replace(key, comic);
        } catch (Exception e) {
            System.out.println("Error: comic is not in this collection.");
        }
    }

    /**
     * Sets the algorithm for decorating a comic in the collection
     * @param decoratorStrategy - concrete strategy
     */
    public void setDecoratorStrategy(DecoratorStrategy decoratorStrategy) {
        this.decoratorStrategy = decoratorStrategy;
    }

    /**
     * Slabs, signs, or authenticates a comic based on the selected strategy
     * @param comic - comic being decorated
     */
    public void decorateComic(Comic comic) {
        try {
            int key = comic.getId();
            comic = collection.get(key);
            comic = decoratorStrategy.decorate(comic);
            collection.replace(key, comic);
        } catch (Exception e) {
            System.out.println("Error: comic is not in this collection.");
        }
    }
    /**
     * Sets the algorithm for editing a comic in the collection
     * @param editStrategy - concrete strategy
     */
    public void setEditStrategy(EditStrategy editStrategy) {
        this.editStrategy = editStrategy;
    }

    /**
     * Edits a comic and puts it back into the collection
     * @param comic - comic being edited
     * @param attribute - the new attribute being added or modified
     */
    public void editComic(Comic comic, String attribute) {
        collection.replace(comic.getId(), editStrategy.edit(comic, attribute));
    }

    /**
     * Sets the algorithm for searching for a comic in the collection
     * @param searchStrategy - concrete strategy
     */
    @Override
    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    /**
     * Returns the comics that turned up in the search
     * @param term - search term
     * @param exactMatch - decides whether the search term has to be met exactly
     * @return
     */
    @Override
    public ArrayList<Comic> search(String term, boolean exactMatch) {
        return searchStrategy.search(collection, term, exactMatch);
    }

    /**
     * Sets the algorithm for sorting the collection
     * @param sortStrategy - concrete strategy
     */
    @Override
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    /**
     * Sorts the collection according to the chosen algorithm
     * @return
     */
    @Override
    public ArrayList<Comic> sort(ArrayList<Comic> comics) {
        return sortStrategy.sort(comics);
    }

    /**
     * Allows users to update the name of their collections
     * @param name - new collection name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's given name of the collection
     * @return - name of collection
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the entire collection
     * @return - collection of comics
     */
    @Override
    public ArrayList<Comic> getCollection() {
        return new ArrayList<>(collection.values());
    }

    /**
     * Returns the current number of issues in the collection
     * @return - number of issues
     */
    @Override
    public int getNumberOfIssues() {
        return numberOfIssues;
    }

    /**
     * Calculates and returns the value of the entire collection
     * @return - added value of all comics in collection
     */
    public double getValue() {
        double value = 0;

        for (Map.Entry<Integer, Comic> comicEntry : collection.entrySet()) {
            //how's that for a weird looking call. comicEntry.getValue() gets the comic
            //comicEntry.getValue().getValue() gets the value of the comic
            value = value + comicEntry.getValue().getValue();
        }

        return value;
    }

    @Override
    public Comic getComic(int id) {
        return collection.get(id);
    }
}