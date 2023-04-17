package src.tests.model.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.commons.annotation.Testable;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.collections.PersonalCollection;
import src.model.comics.AuthenticatedComic;
import src.model.comics.Comic;
import src.model.comics.ComicBook;
import src.model.comics.GradedComic;
import src.model.comics.SignedComic;
import src.model.comics.SlabbedComic;
import src.model.command.AddComic;
import src.model.command.AuthenticateComic;
import src.model.command.EditComic;
import src.model.command.GradeComic;
import src.model.command.RemoveComic;
import src.model.command.SignComic;
import src.model.command.SlabComic;
import src.model.users.User;
import src.persistance.ComicCSVReader;

@Testable
public class CommandTest {

    Comic comic;
    PersonalCollection collection;
    Map<String, String> changes;
    User user;

    @BeforeEach
    public void setUp() throws Exception {
        ComicCSVReader reader = new ComicCSVReader("data/comics.csv");
        ComicCollection database = new DatabaseCollection();
        database = reader.parseComics();
        comic = database.getCollection().get(1);
        user = new User("Bill", "Password");
        user.addPersonalCollection("Hello There");
        collection = user.getPersonalCollections().get("Hello There");
        System.out.println(collection);
        changes = new HashMap<>();
        changes.put("title", "New Title");
    }

    @Test
    public void testAddComic() throws Exception {
        ComicCSVReader reader = new ComicCSVReader("resources/comics.csv");
        ComicCollection database = new DatabaseCollection();
        database = reader.parseComics();
        comic = database.getCollection().get(1);
        user = new User("Bill", "Password");
        user.addPersonalCollection("Hello There");
        collection = user.getPersonalCollections().get("Hello There");
        System.out.println(collection);
        changes = new HashMap<>();
        changes.put("title", "New Title");
        AddComic command = new AddComic(comic, collection);
        command.execute();
        assertTrue(collection.getCollection().containsValue(comic));
        command.undo();
        assertFalse(collection.getCollection().containsValue(comic));
        command.redo();
        assertTrue(collection.getCollection().containsValue(comic));
    }

    @Test
    public void testAuthenticateComic() {
        SignedComic signedComic = new SignedComic(comic);
        AuthenticateComic command = new AuthenticateComic(signedComic);
        command.execute();
        assertTrue(command.getDecoratedComic() instanceof AuthenticatedComic);
    }

    @Test
    public void testEditComic() {
        EditComic command = new EditComic(comic, changes);
        command.execute();
        assertEquals("New Title", comic.getTitle());
        command.undo();
        assertNotEquals("New Title", comic.getTitle());
        command.redo();
        assertEquals("New Title", comic.getTitle());
    }

    @Test
    public void testGradeComic() {
        int grade = 9;
        GradeComic command = new GradeComic(comic, grade);
        command.execute();
        assertTrue(command.getDecoratedComic() instanceof GradedComic);
        assertEquals(9, ((GradedComic) command.getDecoratedComic()).getValue(), 0.00001);
    }

    @Test
    public void testRemoveComic() {
        collection.addComic(comic);
        RemoveComic command = new RemoveComic(collection, comic);
        command.execute();
        assertFalse(collection.getCollection().containsValue(comic));
        command.undo();
        assertTrue(collection.getCollection().containsValue(comic));
        command.redo();
        assertFalse(collection.getCollection().containsValue(comic));
    }

    @Test
    public void testSignComic() {
        SignComic command = new SignComic(comic);
        command.execute();
        assertTrue(command.getDecoratedComic() instanceof SignedComic);
    }

    @Test
    public void testSlabComic() {
        GradedComic gradedComic = new GradedComic(comic, 9);
        SlabComic command = new SlabComic(gradedComic);
        command.execute();
        assertTrue(command.getDecoratedComic() instanceof SlabbedComic);
    }
}