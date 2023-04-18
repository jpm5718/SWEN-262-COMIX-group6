/**
 * @author Dan Corcoran
 */


package src.model.collections.sort;

import src.model.comics.Comic;
import java.util.ArrayList;
import java.util.Collections;

public class SortByDateAdded implements SortStrategy {

    @Override
    public ArrayList<Comic> sort(ArrayList<Comic> comics) {
        Comic comicsArr[] = new Comic[comics.size()];

        for (int i = 0; i < comicsArr.length; i++) {
            comicsArr[i] = comics.get(i);
        }

        insertionSort(comicsArr, comicsArr.length - 1);

        ArrayList<Comic> sorted = new ArrayList<>();
        Collections.addAll(sorted, comicsArr);
        
        return sorted;
    }

    public void insertionSort(Comic comicsArr[], int n) {
        
        if (n <= 1) {
            return;
        }

        insertionSort(comicsArr, n - 1);

        Comic last = comicsArr[n - 1];
        int j = n - 2;

        while (j >= 0 && comicsArr[j].getDateAdded().compareTo(last.getDateAdded()) > 1) {
            comicsArr[j + 1] = comicsArr[j];
            j--;
        }

        comicsArr[j + 1] = last;
    }
}
