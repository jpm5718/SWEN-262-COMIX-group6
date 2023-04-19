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
import src.model.collections.search.SearchByIssue;
import src.model.collections.search.SearchByPublisher;
import src.model.collections.search.SearchByReleaseDate;
import src.model.collections.search.SearchByRuns;
import src.model.collections.search.SearchBySeries;
import src.model.collections.search.SearchByTitle;
import src.model.collections.search.SearchByVarDesc;
import src.model.collections.sort.SortByDateAdded;
import src.model.collections.sort.SortByFormat;
import src.model.collections.sort.SortByIssue;
import src.model.collections.sort.SortByReleaseDate;
import src.model.collections.sort.SortBySeries;
import src.model.collections.sort.SortByTitle;
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
    public void testGetName() {
        PersonalCollection test = new PersonalCollection("Test");
        String expected = "Test";
        Assertions.assertEquals(expected, test.getName());
    }

    @Test
    public void testSetName() {
        PersonalCollection test = new PersonalCollection("Test");
        test.setName("tseT");
        String expected = "tseT";
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
                test2.addComic(comic);
            }
        }

        test2.setSearchStrategy(new SearchByGaps());
        actual = test2.search("Issue", false);

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void testSearchByIssue() {
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
            
            if (i == 5) {
                expected.add(comic);
            }
        }


        test.setSearchStrategy(new SearchByIssue());
        ArrayList<Comic> actual = test.search("5", true);
        Assertions.assertTrue(actual.isEmpty());

        actual = test.search("5", false);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        actual = test.search("Issue 5", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        expected = test.getCollection();

        actual = test.search("Issue", false);
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testSearchByPublisher() {
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
            
            if (i == 9) {
                expected.add(comic);
            }
        }


        test.setSearchStrategy(new SearchByPublisher());
        ArrayList<Comic> actual = test.search("2", true);
        Assertions.assertTrue(actual.isEmpty());

        actual = test.search("9", false);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        actual = test.search("Publisher 9", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        expected = test.getCollection();

        actual = test.search("Publisher", false);
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testSearchByReleaseDate() {
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
            
            if (i == 1) {
                expected.add(comic);
            }
        }


        test.setSearchStrategy(new SearchByReleaseDate());
        ArrayList<Comic> actual = test.search("1", true);
        Assertions.assertTrue(actual.isEmpty());

        actual = test.search("1", false);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        actual = test.search("Release Date 1", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        expected = test.getCollection();

        actual = test.search("Release", false);
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testSearchByRuns() {
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

            test1.addComic(comic);
        }


        test1.setSearchStrategy(new SearchByRuns());
        ArrayList<Comic> actual = test1.search("Issue", false);

        Assertions.assertEquals(15, actual.size());

        for (int i = 15; i < 25; i++) {

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

            if (i != 17) {
                test1.addComic(comic);
            }
            
        }
        
        actual = test1.search("Issue", false);

        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals(17, actual.size());

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
                test2.addComic(comic);
            }
        }

        test2.setSearchStrategy(new SearchByRuns());
        actual = test2.search("Issue", false);

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void testSearchBySeries() {
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
            
            if (i == 8) {
                expected.add(comic);
            }
        }


        test.setSearchStrategy(new SearchBySeries());
        ArrayList<Comic> actual = test.search("8", true);
        Assertions.assertTrue(actual.isEmpty());

        actual = test.search("8", false);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        actual = test.search("Series 8", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        expected = test.getCollection();

        actual = test.search("Series", false);
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testSearchByTitle() {
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
            
            if (i == 6) {
                expected.add(comic);
            }
        }


        test.setSearchStrategy(new SearchByTitle());
        ArrayList<Comic> actual = test.search("6", true);
        Assertions.assertTrue(actual.isEmpty());

        actual = test.search("6", false);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        actual = test.search("Title 6", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        expected = test.getCollection();

        actual = test.search("Title", false);
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testSearchByVarDesc() {
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


        test.setSearchStrategy(new SearchByVarDesc());
        ArrayList<Comic> actual = test.search("3", true);
        Assertions.assertTrue(actual.isEmpty());

        actual = test.search("3", false);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        actual = test.search("Variant Description 3", true);

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());

        expected = test.getCollection();

        actual = test.search("Variant Description", false);
        Assertions.assertEquals(expected.size(), actual.size());
    }

    public Comic[] sortGen() {
        Comic[] comics = new Comic[5];
        Queue<String> attributes = new LinkedList<>();

        for (int i = 1; i <= 5; i ++) {
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
            comics[i - 1] = comic;
        }

        return comics;
    }

    @Test
    public void testSortByDateAdded() {
        PersonalCollection test = new PersonalCollection("Test");
        ArrayList<Comic> expected = new ArrayList<>();

       Comic[] comics = sortGen();

        test.addComic(comics[3]);
        test.addComic(comics[2]);
        test.addComic(comics[4]);
        test.addComic(comics[1]);
        test.addComic(comics[0]);

        expected.add(comics[0]);
        expected.add(comics[1]);
        expected.add(comics[2]);
        expected.add(comics[3]);
        expected.add(comics[4]);


        test.setSortStrategy(new SortByDateAdded());
        ArrayList<Comic> actual = test.sort(test.getCollection());

        for (int i = 0; i < actual.size(); i++) {
            Assertions.assertEquals(expected.get(i).getId(), actual.get(i).getId());
        }
    }

    @Test
    public void testSortByFormat() {
        PersonalCollection test = new PersonalCollection("Test");
        ArrayList<Comic> expected = new ArrayList<>();

       Comic[] comics = sortGen();

        test.addComic(comics[2]);
        test.addComic(comics[1]);
        test.addComic(comics[3]);
        test.addComic(comics[0]);
        test.addComic(comics[4]);

        expected.add(comics[0]);
        expected.add(comics[1]);
        expected.add(comics[2]);
        expected.add(comics[3]);
        expected.add(comics[4]);


        test.setSortStrategy(new SortByFormat());
        ArrayList<Comic> actual = test.sort(test.getCollection());

        for (int i = 0; i < actual.size(); i++) {
            Assertions.assertEquals(expected.get(i).getId(), actual.get(i).getId());
        }
    }

    @Test
    public void testSortByIssue() {
        PersonalCollection test = new PersonalCollection("Test");
        ArrayList<Comic> expected = new ArrayList<>();

       Comic[] comics = sortGen();

        test.addComic(comics[0]);
        test.addComic(comics[1]);
        test.addComic(comics[3]);
        test.addComic(comics[4]);
        test.addComic(comics[2]);

        expected.add(comics[0]);
        expected.add(comics[1]);
        expected.add(comics[2]);
        expected.add(comics[3]);
        expected.add(comics[4]);


        test.setSortStrategy(new SortByIssue());
        ArrayList<Comic> actual = test.sort(test.getCollection());

        for (int i = 0; i < actual.size(); i++) {
            Assertions.assertEquals(expected.get(i).getId(), actual.get(i).getId());
        }
    }

    @Test
    public void testSortByReleaseDate() {
        PersonalCollection test = new PersonalCollection("Test");
        ArrayList<Comic> expected = new ArrayList<>();

       Comic[] comics = sortGen();

        test.addComic(comics[3]);
        test.addComic(comics[2]);
        test.addComic(comics[4]);
        test.addComic(comics[1]);
        test.addComic(comics[0]);

        expected.add(comics[0]);
        expected.add(comics[1]);
        expected.add(comics[2]);
        expected.add(comics[3]);
        expected.add(comics[4]);


        test.setSortStrategy(new SortByReleaseDate());
        ArrayList<Comic> actual = test.sort(test.getCollection());

        for (int i = 0; i < actual.size(); i++) {
            Assertions.assertEquals(expected.get(i).getId(), actual.get(i).getId());
        }
    }

    @Test
    public void testSortBySeries() {
        PersonalCollection test = new PersonalCollection("Test");
        ArrayList<Comic> expected = new ArrayList<>();

       Comic[] comics = sortGen();

        test.addComic(comics[1]);
        test.addComic(comics[2]);
        test.addComic(comics[3]);
        test.addComic(comics[0]);
        test.addComic(comics[4]);

        expected.add(comics[0]);
        expected.add(comics[1]);
        expected.add(comics[2]);
        expected.add(comics[3]);
        expected.add(comics[4]);


        test.setSortStrategy(new SortBySeries());
        ArrayList<Comic> actual = test.sort(test.getCollection());

        for (int i = 0; i < actual.size(); i++) {
            Assertions.assertEquals(expected.get(i).getId(), actual.get(i).getId());
        }
    }

    @Test
    public void testSortByTit() {
        PersonalCollection test = new PersonalCollection("Test");
        ArrayList<Comic> expected = new ArrayList<>();

       Comic[] comics = sortGen();

        test.addComic(comics[4]);
        test.addComic(comics[3]);
        test.addComic(comics[2]);
        test.addComic(comics[1]);
        test.addComic(comics[0]);

        expected.add(comics[0]);
        expected.add(comics[1]);
        expected.add(comics[2]);
        expected.add(comics[3]);
        expected.add(comics[4]);


        test.setSortStrategy(new SortByTitle());
        ArrayList<Comic> actual = test.sort(test.getCollection());

        for (int i = 0; i < actual.size(); i++) {
            Assertions.assertEquals(expected.get(i).getId(), actual.get(i).getId());
        }
    }

    @Test
    public void testGetCollection() {
        PersonalCollection test = new PersonalCollection("Test");
        ArrayList<Comic> expected = new ArrayList<>();

       Comic[] comics = sortGen();

        test.addComic(comics[0]);
        test.addComic(comics[1]);
        test.addComic(comics[2]);
        test.addComic(comics[3]);
        test.addComic(comics[4]);

        expected.add(comics[0]);
        expected.add(comics[1]);
        expected.add(comics[2]);
        expected.add(comics[3]);
        expected.add(comics[4]);

        ArrayList<Comic> actual = test.getCollection();

        for (int i = 0; i < actual.size(); i++) {
            Assertions.assertEquals(expected.get(i).getId(), actual.get(i).getId());
        }
    }

    @Test
    public void testGetNumberOfIssues() {
        PersonalCollection test = new PersonalCollection("Test");
        int expected = 5;

       Comic[] comics = sortGen();

        test.addComic(comics[0]);
        test.addComic(comics[1]);
        test.addComic(comics[2]);
        test.addComic(comics[3]);
        test.addComic(comics[4]);

        int actual = test.getNumberOfIssues();

        Assertions.assertEquals(expected, actual);
    }

    // @Test
    // public void testGetValue() {
    //     PersonalCollection test = new PersonalCollection("Test");
    //     double expected = 0;

    //     Comic[] comics = sortGen();

    //     for (int i = 0; i < comics.length; i++) {
    //         expected = expected + comics[i].getValue();
    //     }

    //     test.addComic(comics[0]);
    //     test.addComic(comics[1]);
    //     test.addComic(comics[2]);
    //     test.addComic(comics[3]);
    //     test.addComic(comics[4]);

    //     double actual = test.getValue();

    //     Assertions.assertEquals(expected, actual);
    // }

    @Test
    public void testGetComic() {
        PersonalCollection test = new PersonalCollection("Test");

       Comic[] comics = sortGen();

        test.addComic(comics[0]);
        test.addComic(comics[1]);
        test.addComic(comics[2]);
        test.addComic(comics[3]);
        test.addComic(comics[4]);

        Comic expected = comics[3];

        Comic actual = test.getComic(comics[3].getId());
        Assertions.assertEquals(expected.getId(), actual.getId());
    }
}