/**
 * @author Dan Corcoran
 */


package src.model.collections.sort;

import src.model.comics.Comic;
import java.util.ArrayList;
import java.util.Collections;

public class SortByReleaseDate implements SortStrategy {

    @Override
    public ArrayList<Comic> sort(ArrayList<Comic> comics) {
        Comic comicsArr[] = new Comic[comics.size()];

        for (int i = 0; i < comicsArr.length; i++) {
            comicsArr[i] = comics.get(i);
        }

        insertionSort(comicsArr, comicsArr.length);

        ArrayList<Comic> sorted = new ArrayList<>();
        Collections.addAll(sorted, comicsArr);

        for (int i = 0; i < comicsArr.length; i++) {
            System.out.println("\n" + comicsArr[i].getReleaseDate());
        }
        
        return sorted;
    }

    public void insertionSort(Comic comicsArr[], int n) {
        
        if (n <= 1) {
            return;
        }

        insertionSort(comicsArr, n - 1);

        Comic last = comicsArr[n - 1];
        int j = n - 2;

        while (j >= 0 && comicsArr[j].getReleaseDate().compareTo(last.getReleaseDate()) > 0) {
            comicsArr[j + 1] = comicsArr[j];
            j--;
        }

        comicsArr[j + 1] = last;
    }
}
