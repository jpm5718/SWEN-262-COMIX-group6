package src.tests.model.collections.ImportTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.collections.exportComic.Export;
import src.model.collections.exportComic.XMLExport;
import src.model.collections.importComic.Import;
import src.model.collections.importComic.XMLImport;
import src.model.comics.Comic;
import src.model.comics.ComicBook;

public class XMLImportTest {
    @Test
    public void testXMLImport() throws IOException {
        //Export a collection to a csv file - needed for testing import
        DatabaseCollection test = new DatabaseCollection();
         Queue<String> attributes = new LinkedList<>();
 
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
         }

         Export testExport = new XMLExport("test");
         testExport.exportCollection(test);

         //Test for importing;
         Import testImport = new XMLImport("test");
         ComicCollection testCollection = testImport.importCollection(1);
         assertNotNull("collection should exist!", testCollection);
    }
}
