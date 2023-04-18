/**
 * @author Dan Corcoran
 */


package src.model.collections.sort;

import src.model.comics.Comic;

import java.util.ArrayList;
import java.util.Arrays;

public class SortByDateAdded implements SortStrategy {

    @Override
    public ArrayList<Comic> sort(ArrayList<Comic> comics) {
        Comic[] comicsArr = comics.toArray(new Comic[comics.size()]);
        comicsArr = quickSortHelper(comicsArr);

        return new ArrayList<>(Arrays.asList(comicsArr));
    }

    public Comic[] quickSortHelper(Comic[] comics) {
        quickSort(comics, 0, comics.length - 1);
        return comics;
    }

    public void quickSort(Comic[] comics, int start, int end) {
        if (start >= end) {
            return;
        }

        Comic pivot = comics[start];

        int i = start - 1;
        int j = end + 1;

        while (i < j) {
            i++;

            System.out.println("\ni : " + i + " : j : " + j + "\n");
            System.out.println("\ncomic1 : " + comics[i].getId() + " : pivot : " + pivot.getId() + "\n");

            while (comics[i].getDateAdded().compareTo(pivot.getDateAdded()) < 0) {
                j--;
            }

            if (i < j) {
                Comic temp = comics[i];
                comics[i] = comics[j];
                comics[j] = temp;
            }
        }
        quickSort(comics, start, j);
        quickSort(comics, j + 1, end);
    }
}
