package src.tests.model.comics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;
import org.junit.jupiter.api.Tag;

import src.model.comics.Comic;
import src.model.comics.ComicBook;

/**
 * unit test suite for ComicBook.java
 * @author James McGuire
 */

 @Tag("Model-Tier")
public class ComicBookTest {
    
    @Test
    public void testConstructor(){
        //Setup data fields
        String expected_series = "The James Series";
        String expected_issue = "1";
        String expected_title = "James' unit tests";
        String expected_varDesc = "blah blah blah blah blah";
        String expected_publisher = "swen-262 publishing";
        String expected_releaseDate = "June 2023";
        String expected_format = "Comic";
        String expected_dateAdded = "April 2023";
        String expected_creators = "JAMES";
        String expected_id = "15000";

        //add these fields to a queue to be used in the constructor
        Queue<String> data = new LinkedList<>();
        data.add(expected_series);
        data.add(expected_issue);
        data.add(expected_title);
        data.add(expected_varDesc);
        data.add(expected_publisher);
        data.add(expected_releaseDate);
        data.add(expected_format);
        data.add(expected_dateAdded);
        data.add(expected_creators);
        data.add(expected_id);

        //create ComicBook object
        Comic comic = new ComicBook(data);

        //assertions
        assertEquals(expected_series, comic.getSeries());
        assertEquals(expected_issue, comic.getIssue());
        assertEquals(expected_title, comic.getFullTitle());
        assertEquals(expected_varDesc, comic.getVarDesc());
        assertEquals(expected_publisher, comic.getPublisher());
        assertEquals(expected_releaseDate, comic.getReleaseDate());
        assertEquals(expected_format, comic.getFormat());
        assertEquals(expected_dateAdded, comic.getDateAdded());
        assertEquals(expected_creators, comic.getCreators());
        assertEquals(Integer.parseInt(expected_id), comic.getId());
    }
}
