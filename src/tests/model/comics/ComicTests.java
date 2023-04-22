package src.tests.model.comics;

import static org.junit.Assert.*;
import java.util.Queue;

import src.model.comics.Comic;
import src.model.comics.ComicBook;
import src.model.comics.GradedComic;
import src.model.comics.SignedComic;
import src.model.comics.SlabbedComic;

import org.junit.Test;
import java.util.LinkedList;

public class ComicTests {

    // ComicBook tests
    @Test
    public void testComicBookGetters() {
        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Publisher 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Creators 1");
        attributes.add("123");
        Comic comic = new ComicBook(attributes);
        assertEquals("Series 1", comic.getSeries());
        assertEquals("Issue 1", comic.getIssue());
        assertEquals("Title 1", comic.getTitle());
        assertEquals("Variant Description 1", comic.getVarDesc());
        assertEquals("Release Date 1", comic.getReleaseDate());
        assertEquals("Format 1", comic.getFormat());
        assertEquals("Date Added 1", comic.getDateAdded());
        assertEquals(9.99, comic.getValue(), 0.01);
        assertEquals(123, comic.getId());
    }

    // GradedComic tests
    @Test
    public void testGradedComicValueGrade1() {
        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Publisher 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Creators 1");
        attributes.add("123");
        Comic comic = new ComicBook(attributes);
        GradedComic gradedComic = new GradedComic(comic, 1);
        assertEquals(0.999, gradedComic.getValue(), 0.001);
    }

    @Test
    public void testGradedComicValueGrade2() {
        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Publisher 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Creators 1");
        attributes.add("123");
        Comic comic = new ComicBook(attributes);
        GradedComic gradedComic = new GradedComic(comic, 5);
        assertEquals(6.982, gradedComic.getValue(), 0.001);
    }

    // SignedComic tests
    @Test
    public void testSignedComicValue() {
        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Publisher 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Creators 1");
        attributes.add("123");
        Comic comic = new ComicBook(attributes);
        SignedComic signedComic = new SignedComic(comic);
        assertEquals(10.4945, signedComic.getValue(), 0.01);
    }

    // SlabbedComic tests
    @Test
    public void testSlabbedComicValue() {
        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Publisher 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Creators 1");
        attributes.add("123");
        Comic comic = new ComicBook(attributes);
        SlabbedComic slabbedComic = new SlabbedComic(comic);
        assertEquals(19.98, slabbedComic.getValue(), 0.01);
    }
}