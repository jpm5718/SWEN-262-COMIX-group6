/**
 * @author Dan Corcoran
 */


package src.model.collections.sort;

import src.model.comics.Comic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class SortByDateAdded implements SortStrategy {

    @Override
    public ArrayList<Comic> sort(ArrayList<Comic> comics) {
        Comic comicsArr[] = new Comic[comics.size()];

        for (int i = 0; i < comicsArr.length; i++) {
            comicsArr[i] = comics.get(i);
        }

        try {
            insertionSort(comicsArr, comicsArr.length);
        } catch (Exception ignored){}

        ArrayList<Comic> sorted = new ArrayList<>();
        Collections.addAll(sorted, comicsArr);
        
        return sorted;
    }

    public void insertionSort(Comic comicsArr[], int n) throws ParseException {
        
        if (n <= 1) {
            return;
        }

        insertionSort(comicsArr, n - 1);

        Comic last = comicsArr[n - 1];
        int j = n - 2;


        while (j >= 0 && sortHelper(comicsArr[j], last) > 0) {
            comicsArr[j + 1] = comicsArr[j];
            j--;
        }

        comicsArr[j + 1] = last;
    }

    public int sortHelper(Comic comic, Comic last) throws ParseException {

        String comicDateString = comic.getDateAdded();
        String lastDateString = last.getDateAdded();
        Date comicDate = new Date();;
        Date lastDate = new Date();

        if (comicDateString.charAt(3) == ',') {
            SimpleDateFormat comicFormatter = new SimpleDateFormat("MMM, dd yyyy");
            comicDate = comicFormatter.parse(comicDateString);

        } else if (comicDateString.charAt(6) == ',') {
            SimpleDateFormat comicFormatter = new SimpleDateFormat("MMM dd, yyyy");
            comicDate = comicFormatter.parse(comicDateString);

        } else if ( comicDateString.contains(" ")) {
            SimpleDateFormat comicFormatter = new SimpleDateFormat("MMM yyyy");
            comicDate = comicFormatter.parse(comicDateString);
        } else {
            SimpleDateFormat comicFormatter = new SimpleDateFormat("yyyy");
            comicDate = comicFormatter.parse(comicDateString);
        }

        if (lastDateString.charAt(3) == ',') {
            SimpleDateFormat lastFormatter = new SimpleDateFormat("MMM, dd yyyy");
            lastDate = lastFormatter.parse(lastDateString);

        } else if (lastDateString.charAt(6) == ',') {
            SimpleDateFormat lastFormatter = new SimpleDateFormat("MMM dd, yyyy");
            lastDate = lastFormatter.parse(lastDateString);

        } else if ( lastDateString.contains(" ")) {
            SimpleDateFormat lastFormatter = new SimpleDateFormat("MMM yyyy");
            lastDate = lastFormatter.parse(lastDateString);
        } else {
            SimpleDateFormat lastFormatter = new SimpleDateFormat("yyyy");
            lastDate = lastFormatter.parse(lastDateString);
        }

        return comicDate.compareTo(lastDate);
    }
}
