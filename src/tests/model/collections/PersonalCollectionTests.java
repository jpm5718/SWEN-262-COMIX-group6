/**
 * @author Dan Corcoran
 */

package src.tests.model.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import src.model.collections.PersonalCollection;
import src.model.collections.editComic.SeriesEditor;
import src.model.collections.modifyComicType.SignStrategy;
import src.model.collections.search.SearchByComicType;
import src.model.collections.search.SearchByCreators;
import src.model.collections.search.SearchByDateAdded;
import src.model.collections.search.SearchByFormat;
import src.model.collections.search.SearchByGaps;
import src.model.comics.Comic;
import src.model.comics.ComicBook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Tag("Model-Tier")
public class PersonalCollectionTests {

    @Test
    public void testConstructor() {
        PersonalCollection test = new PersonalCollection("Test");
        Assertions.assertNotNull(test);
    }

    @Test
    public void testName() {
        PersonalCollection test = new PersonalCollection("Test");
        String expected = "Test";
        Assertions.assertEquals(expected, test.getName());
    }

    @Test
    public void testAddComicByComic() {
        PersonalCollection test = new PersonalCollection("Test");

        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Publisher 1");
        attributes.add("Creators 1");
        attributes.add("111");

        Comic comic1 = new ComicBook(attributes);

        ArrayList<Comic> expected = new ArrayList<>();
        expected.add(comic1);

        test.addComic(comic1);
        ArrayList<Comic> actual = test.getCollection();

        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    public void testAddComicByAttributes() {
        PersonalCollection test = new PersonalCollection("Test");

        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Publisher 1");
        attributes.add("Creators 1");
        attributes.add("111");

        Comic expected = new ComicBook(attributes);

        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Publisher 1");
        attributes.add("Creators 1");
        attributes.add("111");

        test.addComic(attributes);
        Comic actual = test.getCollection().get(0);


        Assertions.assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void testRemoveComic() {
        PersonalCollection test = new PersonalCollection("Test");

        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Publisher 1");
        attributes.add("Creators 1");
        attributes.add("111");

        Comic comic1 = new ComicBook(attributes);

        attributes.clear();
        attributes.add("Series 2");
        attributes.add("Issue 2");
        attributes.add("Title 2");
        attributes.add("Variant Description 2");
        attributes.add("Release Date 2");
        attributes.add("Format 2");
        attributes.add("Date Added 2");
        attributes.add("Publisher 2");
        attributes.add("Creators 2");
        attributes.add("222");

        Comic comic2 = new ComicBook(attributes);

        ArrayList<Comic> expected = new ArrayList<>();
        expected.add(comic2);

        test.addComic(comic1);
        test.addComic(comic2);
        ArrayList<Comic> actual = test.getCollection();

        Assertions.assertNotEquals(expected.get(0), actual.get(0));

        test.removeComic(comic1);
        actual = test.getCollection();

        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

    @Test
    public void testGradeComic() {
        PersonalCollection test = new PersonalCollection("Test");

        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Publisher 1");
        attributes.add("Creators 1");
        attributes.add("111");

        Comic comic1 = new ComicBook(attributes);
        test.addComic(comic1);
        test.gradeComic(comic1, 8);

        double expected = (Math.log10(8) * 9.99);
        double actual = test.getComic(comic1.getId()).getValue();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDecorateComic() {
        PersonalCollection test = new PersonalCollection("Test");

        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Publisher 1");
        attributes.add("Creators 1");
        attributes.add("111");

        Comic comic1 = new ComicBook(attributes);
        test.addComic(comic1);

        test.setDecoratorStrategy(new SignStrategy());
        test.decorateComic(comic1);
        double expected = (9.99 * 1.05);
        double actual = test.getComic(comic1.getId()).getValue();
        Assertions.assertEquals(expected, actual);

        test.decorateComic(comic1);
        expected = (expected * 1.05);
        actual = test.getComic(comic1.getId()).getValue();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEditComic() {
        PersonalCollection test = new PersonalCollection("Test");

        Queue<String> attributes = new LinkedList<>();
        attributes.add("Series 1");
        attributes.add("Issue 1");
        attributes.add("Title 1");
        attributes.add("Variant Description 1");
        attributes.add("Release Date 1");
        attributes.add("Format 1");
        attributes.add("Date Added 1");
        attributes.add("Publisher 1");
        attributes.add("Creators 1");
        attributes.add("111");

        Comic expected = new ComicBook(attributes);
        test.addComic(expected);

        test.setEditStrategy(new SeriesEditor());
        test.editComic(expected, "Series 2");

        Comic actual = test.getComic(expected.getId());

        expected.setSeries("Series 2");

        Assertions.assertEquals(expected.getSeries(), actual.getSeries());
    }

    @Test
    public void testSearchByComicType() {
        PersonalCollection test = new PersonalCollection("Test");
        Queue<String> attributes = new LinkedList<>();
        ArrayList<Comic> expected = new ArrayList<>();

        for (int i = 0; i < 10; i ++) {
            attributes.add("Series " + i);
            attributes.add("Issue " + i);
            attributes.add("Title " + i);
            attributes.add("Variant Description " + i);
            attributes.add("Release Date " + i);
            attributes.add("Format " + i);
            attributes.add("Date Added " + i);
            attributes.add("Publisher " + i);
            attributes.add("Creators " + i);
            attributes.add("" + i);
            Comic comic = new ComicBook(attributes);
            test.addComic(comic);

            if (i % 4 == 0) {
                test.gradeComic(comic, i);
                expected.add(comic);
            }
        }


        test.setSearchStrategy(new SearchByComicType());
        ArrayList<Comic> actual = test.search("graded", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());
        Assertions.assertEquals(expected.get(1).getId(), actual.get(1).getId());

        actual = test.search("slabbed", true);
        Assertions.assertTrue(actual.isEmpty());
    }
    
    @Test
    public void testSearchByCreators() {
        PersonalCollection test = new PersonalCollection("Test");
        Queue<String> attributes = new LinkedList<>();
        ArrayList<Comic> expected = new ArrayList<>();

        for (int i = 0; i < 10; i ++) {
            attributes.add("Series " + i);
            attributes.add("Issue " + i);
            attributes.add("Title " + i);
            attributes.add("Variant Description " + i);
            attributes.add("Release Date " + i);
            attributes.add("Format " + i);
            attributes.add("Date Added " + i);
            attributes.add("Publisher " + i);
            attributes.add("Creators " + i);
            attributes.add("" + i);
            Comic comic = new ComicBook(attributes);
            test.addComic(comic);
            
            if (i == 3) {
                expected.add(comic);
            }
        }


        test.setSearchStrategy(new SearchByCreators());
        ArrayList<Comic> actual = test.search("3", true);
        Assertions.assertTrue(actual.isEmpty());

        actual = test.search("3", false);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        actual = test.search("Creators 3", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        expected = test.getCollection();

        actual = test.search("Creators", false);
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testSearchByDateAdded() {
        PersonalCollection test = new PersonalCollection("Test");
        Queue<String> attributes = new LinkedList<>();
        ArrayList<Comic> expected = new ArrayList<>();

        for (int i = 0; i < 10; i ++) {
            attributes.add("Series " + i);
            attributes.add("Issue " + i);
            attributes.add("Title " + i);
            attributes.add("Variant Description " + i);
            attributes.add("Release Date " + i);
            attributes.add("Format " + i);
            attributes.add("Date Added " + i);
            attributes.add("Publisher " + i);
            attributes.add("Creators " + i);
            attributes.add("" + i);
            Comic comic = new ComicBook(attributes);
            test.addComic(comic);
            
            if (i == 7) {
                expected.add(comic);
            }
        }


        test.setSearchStrategy(new SearchByDateAdded());
        ArrayList<Comic> actual = test.search("7", true);
        Assertions.assertTrue(actual.isEmpty());

        actual = test.search("7", false);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        actual = test.search("Date Added 7", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        expected = test.getCollection();

        actual = test.search("Date Added", false);
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testSearchByFormat() {
        PersonalCollection test = new PersonalCollection("Test");
        Queue<String> attributes = new LinkedList<>();
        ArrayList<Comic> expected = new ArrayList<>();

        for (int i = 0; i < 10; i ++) {
            attributes.add("Series " + i);
            attributes.add("Issue " + i);
            attributes.add("Title " + i);
            attributes.add("Variant Description " + i);
            attributes.add("Release Date " + i);
            attributes.add("Format " + i);
            attributes.add("Date Added " + i);
            attributes.add("Publisher " + i);
            attributes.add("Creators " + i);
            attributes.add("" + i);
            Comic comic = new ComicBook(attributes);
            test.addComic(comic);
            
            if (i == 2) {
                expected.add(comic);
            }
        }


        test.setSearchStrategy(new SearchByFormat());
        ArrayList<Comic> actual = test.search("2", true);
        Assertions.assertTrue(actual.isEmpty());

        actual = test.search("2", false);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        actual = test.search("Format 2", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        expected = test.getCollection();

        actual = test.search("Format", false);
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testSearchByGaps() {
        PersonalCollection test1 = new PersonalCollection("Test");
        PersonalCollection test2 = new PersonalCollection("Test");
        Queue<String> attributes = new LinkedList<>();

        for (int i = 0; i < 15; i ++) {

            attributes.add("Series " + i);
            attributes.add("Issue " + i);
            attributes.add("Title " + i);
            attributes.add("Variant Description " + i);
            attributes.add("Release Date " + i);
            attributes.add("Format " + i);
            attributes.add("Date Added " + i);
            attributes.add("Publisher " + i);
            attributes.add("Creators " + i);
            attributes.add("" + i);
            Comic comic = new ComicBook(attributes);

            if (i != 5 && i != 7) {
                test1.addComic(comic);
            }
        }


        test1.setSearchStrategy(new SearchByGaps());
        ArrayList<Comic> actual = test1.search("Issue", false);

        Assertions.assertEquals(13, actual.size());

        for (int i = 15; i < 30; i++) {

            attributes.add("Series " + i);
            attributes.add("Issue " + i);
            attributes.add("Title " + i);
            attributes.add("Variant Description " + i);
            attributes.add("Release Date " + i);
            attributes.add("Format " + i);
            attributes.add("Date Added " + i);
            attributes.add("Publisher " + i);
            attributes.add("Creators " + i);
            attributes.add("" + i);
            Comic comic = new ComicBook(attributes);

            if (i != 16 && i != 26) {
                test1.addComic(comic);
            }
            
        }
        
        actual = test1.search("Issue", false);

        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals(27, actual.size());

        for (int i = 0; i < 15; i ++) {

            attributes.add("Series " + i);
            attributes.add("Issue " + i);
            attributes.add("Title " + i);
            attributes.add("Variant Description " + i);
            attributes.add("Release Date " + i);
            attributes.add("Format " + i);
            attributes.add("Date Added " + i);
            attributes.add("Publisher " + i);
            attributes.add("Creators " + i);
            attributes.add("" + i);
            Comic comic = new ComicBook(attributes);

            if (i != 5 && i != 7 && i != 11) {
                test1.addComic(comic);
            }
        }

        test2.setSearchStrategy(new SearchByGaps());
        actual = test2.search("Issue", false);

        Assertions.assertTrue(actual.isEmpty());
    }
}