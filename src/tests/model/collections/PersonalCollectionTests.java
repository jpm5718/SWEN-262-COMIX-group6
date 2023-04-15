package src.tests.model.collections;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import src.model.collections.ComicCollection;
import src.model.collections.PersonalCollection;

@Tag("Model-Tier")
public class PersonalCollectionTests {

    PersonalCollection test = new PersonalCollection("Test");

    @Test
    public void testConstructor() {
        assertNotNull(test);

        String expected = "Test";
        assertEquals(expected, test.getName());

        assertTrue();
    }

    @Test
    public void testName() {
        String expected = "Test";
        assertEquals();
    }

}