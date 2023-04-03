/**
 * @author Dan Corcoran
 */

package src.model.collections;

import src.model.collections.display.DisplayStrategy;
import src.model.collections.search.SearchStrategy;
import src.model.collections.search.SearchByTitle;
import src.model.collections.sort.SortByTitle;
import src.model.collections.sort.SortStrategy;
import src.model.comics.*;
import src.model.comics.editComic.EditStrategy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class PersonalCollection implements ComicCollection {

    private Map<Integer, Comic> collection;
    private String name;
    private SearchStrategy searchStrategy;
    private SortStrategy sortStrategy;
    private DisplayStrategy displayStrategy;
    private EditStrategy editStrategy;
    private int numberOfIssues;

    public PersonalCollection(String name) {
        collection = new TreeMap<>();
        this.name = name;

        //default strategies are searching and sorting by title
        searchStrategy = new SearchByTitle();
        sortStrategy = new SortByTitle();
        editStrategy = null;
        numberOfIssues = 0;
    }

    /**
     * Adds a comic to the collection and increments the number of issues
     * Requires a comic object to be sent from the database of comics
     * @param comic - comic being added
     */
    @Override
    public void addComic(Comic comic) {
        collection.put(comic.getId(), comic);
        numberOfIssues++;
    }

    /**
     * Adds a comic to the collection and increments the number of issues
     * Requires a list of attributes that defines the user's comic
     * @param attributes - attributes of the comic being added
     */
    public void addComic(Queue<String> attributes) {
        Comic comic = new ComicBook(attributes);
        collection.put(comic.getId(), comic);
        numberOfIssues++;
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

    //------try to find a way to program the following 4 comic methods to an interface-----//

    /**
     * Grades a comic currently in the collection
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
     * Slabs a comic currently in the collection
     * @param comic - comic being slabbed
     * */
    public void slabComic(Comic comic) {
        try {
            int key = comic.getId();
            comic = collection.get(key);
            comic = new SlabbedComic(comic);
            collection.replace(key, comic);
        } catch (Exception e) {
            System.out.println("Error: comic is not in this collection.");
        }
    }

    /**
     * Signs a comic currently in the collection
     * @param comic - comic being signed
     * */
    public void signComic(Comic comic) {
        try {
            int key = comic.getId();
            comic = collection.get(key);
            comic = new SignedComic(comic);
            collection.replace(key, comic);
        } catch (Exception e) {
            System.out.println("Error: comic is not in this collection.");
        }
    }

    /**
     * Authenticates a comic currently in the collection
     * @param comic - comic being authenticated
     * */
    public void authenticateComic(Comic comic) {
        try {
            int key = comic.getId();
            comic = collection.get(key);
            comic = new AuthenticatedComic(comic);
            collection.replace(key, comic);
        } catch (Exception e) {
            System.out.println("Error: comic is not in this collection.");
        }
    }

    //-------------------------------------------------------------------------------------//

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
     * @return - list of comics from the search
     */
    @Override
    public ArrayList<Comic> search(String term, boolean exactMatch) {
        return searchStrategy.search(term, exactMatch);
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
     */
    @Override
    public void sort() {
        this.collection = sortStrategy.sort(collection);
    }

    /**
     * Sets the algorithm for what subdivision of the collection is displayed
     * @param displayStrategy - concrete strategy
     */
    @Override
    public void setDisplayStrategy(DisplayStrategy displayStrategy) {
        this.displayStrategy = displayStrategy;
    }

    /**
     * Displays the collection according to the chosen display strategy
     */
    @Override
    public void display() {
        displayStrategy.display(collection);
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
    public Map<Integer, Comic> getCollection() {
        return collection;
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
}