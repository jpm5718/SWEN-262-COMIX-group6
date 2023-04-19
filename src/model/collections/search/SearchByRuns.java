/**
 * @author Dan Corcoran
 */


package src.model.collections.search;

import src.model.comics.Comic;

import java.util.ArrayList;

public class SearchByRuns implements SearchStrategy {

    @Override
    public ArrayList<Comic> search(ArrayList<Comic> collection, String term, boolean exactMatch) {
        ArrayList<Comic> copy = new ArrayList<>(collection);
        ArrayList<Comic> results = new ArrayList<>();

        int start = 0;
        int runSize = 1;
        boolean inRun = false;

        for (int i = 0; i < copy.size() - 1; i++) {
            if ((getAscii(copy.get(i).getIssue(), copy.get(i + 1).getIssue()))) {
                inRun = true;
                runSize++;
            } else {
                inRun = false;
            }
            
            if (!inRun || i == copy.size() - 2) {
                if (runSize >= 12) {
                    for (int j = 0; j < runSize; j++) {
                        results.add(copy.get(start + j));
                    }
                }

                if (i != copy.size() - 2) {
                    start = i + 1;
                    runSize = 1;
                }
            }
        }

        return results;
    }

    public boolean getAscii(String current, String next) {

        if (Math.abs(current.length() - next.length()) != 0) {
            if (Math.abs(current.length() - next.length()) > 1) {
                return false;
            }

            int asciiDif = Math.abs(current.charAt(current.length() - 1) - next.charAt(next.length() - 1));
            if (asciiDif == 1 || asciiDif == 9) {
                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < current.length(); i++) {
            int dif = Math.abs(current.charAt(i) - next.charAt(i));
            if(dif > 1 && dif != 9) {
                return false;
            }
        }

        return true;
    }
}
