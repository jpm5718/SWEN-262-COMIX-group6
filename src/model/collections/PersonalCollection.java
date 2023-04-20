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
import java.util.Queue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PersonalCollection implements ComicCollection {
    @JsonDeserialize(contentUsing = ComicDeserializer.class)
    @JsonProperty("collection") private ArrayList<Comic> collection;
    @JsonProperty("name") private String name;
    @JsonProperty("numberOfIssues") private int numberOfIssues;
    @JsonProperty("value") private double value;
    private SearchStrategy searchStrategy;
    private SortStrategy sortStrategy;
    private EditStrategy editStrategy;
    private DecoratorStrategy decoratorStrategy;

    public PersonalCollection(String name) {

        collection = new ArrayList<>();
        this.name = name;

        //default s4trategies are searching and sorting by title
        searchStrategy = new SearchByTitle();
        sortStrategy = new SortByTitle();
        editStrategy = null;
        decoratorStrategy = null;
        numberOfIssues = 0;
    }

    @JsonCreator
    public PersonalCollection(@JsonProperty("collection")ArrayList<Comic> collection, @JsonProperty("name") String name,
    @JsonProperty("numberOfIssues") int numberOfIssues, @JsonProperty("value") double value){
        this.collection = collection;
        this.name = name;
        this.numberOfIssues = numberOfIssues;
        this.value = value;
    }

    /**
     * Adds a comic to the collection and increments the number of issues
     * Requires a comic object to be sent from the database of comics
     * @param comic - comic being added
     */
    @Override
    public void addComic(Comic comic) {
        for (Comic collComic : collection) {
            if (comic.getId() == collComic.getId()) {
                System.out.println("Error: Comic is already in collection\n");
                return;
            }
        }
        collection.add(comic);
        numberOfIssues++;
    }

    /**
     * Adds a comic to the collection and increments the number of issues
     * Requires a list of attributes that defines the user's comic
     * @param attributes - attributes of the comic being added
     */
    public void addComic(Queue<String> attributes) {
        Comic comic = new ComicBook(attributes);
        for (Comic collComic : collection) {
            if (comic.getId() == collComic.getId()) {
                System.out.println("Error: Comic is already in collection\n");
                return;
            }
        }
        collection.add(comic);
        numberOfIssues++;
    }

    /**
     * Removes a comic from the collection and decrements the number of issues
     * Requires a comic object to be sent from the databse of comics
     * Could be updated in the future to allow deletion based on various criteria
     * @param comic - comic being removed
     */
    public void removeComic(Comic comic) {
        for (Comic collComic : collection) {
            if (comic.getId() == collComic.getId()) {
                collection.remove(collComic);
                numberOfIssues--;
                return;
            }
        }
        System.out.println("Error: comic is not in this collection.");
    }

    /**
     * Grades a comic currently in the collection.
     * Cannot be used with decorator strategy due to the extra grade parameter
     * @param comic - comic being graded
     * @param grade - the grade applied to the comic
     */
    public Comic gradeComic(int id, int grade) {
        for (Comic comic : collection) {
            if (id == comic.getId()) {
                int index = collection.indexOf(comic);
                comic = new GradedComic(comic, grade);
                collection.set(index, comic);
                return comic;
            }
        }
        System.out.println("Error: comic is not in this collection.");
        return null;
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
    public Comic decorateComic(int id) {
        for (Comic comic : collection) {
            if (id == comic.getId()) {
                int index = collection.indexOf(comic);
                comic = decoratorStrategy.decorate(comic);
                collection.set(index, comic);
                return comic;
            }
        }
        System.out.println("Error: comic is not in this collection.");
        return null;
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
        for (Comic collComic : collection) {
            if (comic.getId() == collComic.getId()) {
                int index = collection.indexOf(collComic);
                comic = editStrategy.edit(comic, attribute);
                collection.set(index, comic);
                return;
            }
        }
        System.out.println("Error: comic is not in this collection.");
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
     * Sorts the collection (can be a search reduced collection) according to the chosen algorithm
     * @return
     */
    @Override
    public ArrayList<Comic> sort(ArrayList<Comic> comics) {
        return sortStrategy.sort(comics);
    }

    /**
     * Sorts the entire collection according to the chosen algorithm
     * @return
     */
    @Override
    public ArrayList<Comic> sort() {
        return sortStrategy.sort(collection);
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
        this.value = 0;
        for (Comic comic : collection) {
            value = value + comic.getValue();
        }
        return value;
    }

    @Override
    public Comic getComic(int id) {
        for (Comic comic : collection) {
            if (comic.getId() == id) {
                return comic;
            }
        }
        System.out.println("Error: comic is not in this collection.");
        return null;
    }
}