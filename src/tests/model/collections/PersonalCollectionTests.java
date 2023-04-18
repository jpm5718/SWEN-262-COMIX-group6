package src.tests.model.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import src.model.collections.ComicCollection;
import src.model.collections.PersonalCollection;
import src.model.comics.Comic;
import src.model.comics.ComicBook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Tag("Model-Tier")
public class PersonalCollectionTests {

    PersonalCollection test = new PersonalCollection("Test");

    private static Comic comic1;
    private static Comic comic2;
    private static Comic comic3;
    private static Comic comic4;
    private static Comic comic5;

    @Test
    public void createComics() {

        Queue<String> attributes = new LinkedList<>();
        Comic[] testComics = {comic1, comic2, comic3, comic4, comic5};

        for (int i = 1; i <= 5; i++) {
            attributes.add("Series " + i);
            attributes.add("Issue " + i);
            attributes.add("Title " + i);
            attributes.add("Variant Description " + i);
            attributes.add("Release Date " + i);
            attributes.add("Format " + i);
            attributes.add("Date Added " + i);
            attributes.add("Publisher " + i);
            attributes.add("Creators " + i);
            attributes.add("" + i + "" + (i+1)+ "" + (i+2));

            testComics[i] = new ComicBook(attributes);
            attributes.clear();
        }
    }

    @Test
    public void testConstructor() {
        Assertions.assertNotNull(test);
    }

    @Test
    public void testName() {
        String expected = "Test";
        Assertions.assertEquals(expected, test.getName());
    }

    @Test
    public void testAddComic() {
        ArrayList<Comic> expected = new ArrayList<>();
        expected.add(comic1);

        test.addComic(comic1);
        ArrayList<Comic> actual = test.getCollection();

        Assertions.assertEquals(expected.get(0), actual.get(0));
    }

}