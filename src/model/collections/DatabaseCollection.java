/**
 * @author Dan Corcoran
 */

package src.model.collections;

import src.model.collections.search.SearchByTitle;
import src.model.collections.search.SearchStrategy;
import src.model.collections.sort.SortByTitle;
import src.model.collections.sort.SortStrategy;
import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DatabaseCollection implements ComicCollection{

    private Map<Integer, Comic> collection;
    private SearchStrategy searchStrategy;
    private SortStrategy sortStrategy;
    private int numberOfIssues;

    public DatabaseCollection() {
        collection = new TreeMap<>();

        //default strategies are searching and sorting by title
        searchStrategy = new SearchByTitle();
        sortStrategy = new SortByTitle();
        numberOfIssues = 0;
    }

    @Override
    public void addComic(Comic comic) {
        collection.put(comic.getId(), comic);
        numberOfIssues++;
    }

    @Override
    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    @Override
    public ArrayList<Comic> search(String term, boolean exactMatch) {
        return searchStrategy.search(collection, term, exactMatch);
    }

    @Override
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    @Override
    public ArrayList<Comic> sort(ArrayList<Comic> comics) { return sortStrategy.sort(comics); }

    @Override
    public ArrayList<Comic> getCollection() {
        return new ArrayList<>(collection.values());
    }

    @Override
    public int getNumberOfIssues() {
        return numberOfIssues;
    }


    @Override
    public Comic getComic(int id) {
        return collection.get(id);
    }
}
