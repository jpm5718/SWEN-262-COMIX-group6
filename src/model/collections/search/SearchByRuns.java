/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class SearchByRuns implements SearchStrategy {
    @Override
    public void search(Map<Integer, Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> helper = new ArrayList<>(collection.values());
        helper = recursiveHelper(helper);

        for (Comic comic : helper) {
            System.out.println(comic.getId() + ":\t" + comic.getTitle() + "\n");
        }
    }

    public ArrayList<Comic> recursiveHelper(ArrayList<Comic> comics) {
        ArrayList<Comic> results = new ArrayList<>();
        return recursiveSearch(results, comics, 0, comics.get(0), comics.get(0), comics.get(1), 1);
    }

    public ArrayList<Comic> recursiveSearch(ArrayList<Comic> results, ArrayList<Comic> comics, int runSize, Comic start, Comic current, Comic next, int index) {
        if (comics.isEmpty()) {
            return results;
        }

        if (!current.getTitle().equals(next.getTitle()) || !getAscii(current.getIssue(), next.getIssue())) {
            if (runSize >= 12) {
                for (int i = comics.indexOf(start); i < comics.indexOf(current) ; i++) {
                    results.add(comics.get(i));
                    comics.remove(comics.get(i));
                }
            }
        }
    }

    public boolean getAscii(String current, String next) {
        int currentAscii = 0;
        int nextAscii = 0;

        for (int i = 0; i < current.length(); i++) {
            currentAscii += currentAscii + current.charAt(i);
        }

        for (int i = 0; i < next.length(); i++) {
            nextAscii += nextAscii + next.charAt(i);
        }

        return (nextAscii - currentAscii == 1);
    }
}
