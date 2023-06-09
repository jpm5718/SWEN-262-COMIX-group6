/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.*;

import java.util.ArrayList;

public class SearchByComicType implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(ArrayList<Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> results = new ArrayList<>();

        for (Comic comic : collection) {
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

        return results;
    }
}
