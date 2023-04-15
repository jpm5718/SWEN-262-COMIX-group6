/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.*;

import java.util.ArrayList;
import java.util.Map;

public class SearchByComicType implements SearchStrategy {

    @Override
    public void search(Map<Integer, Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        for (Comic comic : collection.values()) {
            switch (term.toLowerCase()) {
                case "slabbed":
                    if ((comic instanceof SlabbedComic)) {
                        results.add(comic);
                    }
                    break;

                case "graded":
                    if ((comic instanceof GradedComic)) {
                        results.add(comic);
                    }
                    break;

                case "signed":
                    if ((comic instanceof SignedComic)) {
                        results.add(comic);
                    }
                    break;

                case "authenticated":
                    if ((comic instanceof AuthenticatedComic)) {
                        results.add(comic);
                    }
                    break;
            }
        }

        for (Comic comic : results) {
            System.out.println(comic.getId() + ":\t" + comic.getTitle() + "\n");
        }
    }
}
