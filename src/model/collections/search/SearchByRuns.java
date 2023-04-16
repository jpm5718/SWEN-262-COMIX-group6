/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Map;

public class SearchByRuns implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(Map<Integer, Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> copy = new ArrayList<>(collection.values());
        ArrayList<Comic> results = new ArrayList<>();

        Comic start = copy.get(0);
        int runSize = 1;

        for (int i = 0; i < copy.size(); i++) {
            for (int j = 0; getAscii(copy.get(i+j).getIssue(), copy.get(i+j+1).getIssue()); j++) {
                runSize++;
            }
            if (runSize >= 12) {
                int startIndex = copy.indexOf(start);
                for (int j = startIndex; j < startIndex + runSize; j++) {
                    results.add(copy.get(startIndex + j));
                }
            }
            start = copy.get(copy.indexOf(start) + runSize);
            runSize = 1;
        }

        return results;
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
