package src.tests.model.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.jupiter.api.Test;
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
import src.model.command.GradeComic;
import src.model.command.RemoveComic;
import src.model.command.SignComic;
import src.model.command.SlabComic;
import src.model.users.User;

@Testable
public class CommandTest {

    Comic comic;
    PersonalCollection collection;
    Map<String, String> changes;
    User user;

    @BeforeEach
    public void setUp() throws Exception {
        ComicCollection database = new DatabaseCollection();
        
        Queue<String> data = new LinkedList<>(Arrays.asList("\"'Mazing Man\",1,\"\"\"Y'know, After A Long Hard Day...\"\"\",,\"DC Comics\",\"Jan 1986\",Comic,\"Feb 23, 2014\",\"Bob Rozakis | Stephen DeStefano | Karl Kesel\""));
        data.add(String.valueOf(1));
        this.comic = new ComicBook(data);
        database.addComic(comic);

        this.collection = new PersonalCollection("Hello There");
        user = new User("Bill", "Password", collection);
        System.out.println(collection);
        changes = new HashMap<>();
        changes.put("title", "New Title");
    }

    @Test
    public void testAddComic() throws Exception {
        AddComic command = new AddComic(comic, collection);
        command.execute();
        assertTrue(collection.getCollection().contains(comic));
        command.undo();
        assertFalse(collection.getCollection().contains(comic));
        command.redo();
        assertTrue(collection.getCollection().contains(comic));
    }

    @Test
    public void testAuthenticateComic() {
        SignedComic signedComic = new SignedComic(comic);
        AuthenticateComic command = new AuthenticateComic(signedComic, collection);
        command.execute();
        assertTrue(command.getDecoratedComic() instanceof AuthenticatedComic);
    }

    @Test
    public void testGradeComic() {
        int grade = 9;
        GradeComic command = new GradeComic(comic, grade, collection);
        command.execute();
        assertTrue(command.getDecoratedComic() instanceof GradedComic);
        assertEquals(9, ((GradedComic) command.getDecoratedComic()).getValue(), 0.00001);
    }

    @Test
    public void testRemoveComic() {
        collection.addComic(comic);
        RemoveComic command = new RemoveComic(comic, collection);
        command.execute();
        assertFalse(collection.getCollection().contains(comic));
        command.undo();
        assertTrue(collection.getCollection().contains(comic));
        command.redo();
        assertFalse(collection.getCollection().contains(comic));
    }

    @Test
    public void testSignComic() {
        SignComic command = new SignComic(comic, collection);
        command.execute();
        assertTrue(command.getDecoratedComic() instanceof SignedComic);
    }

    @Test
    public void testSlabComic() {
        GradedComic gradedComic = new GradedComic(comic, 9);
        SlabComic command = new SlabComic(gradedComic, collection);
        command.execute();
        assertTrue(command.getDecoratedComic() instanceof SlabbedComic);
    }
}