package src.tests.model.collections.ExportTest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import src.model.collections.DatabaseCollection;
import src.model.collections.exportComic.CSVExport;
import src.model.collections.exportComic.Export;
import src.model.comics.Comic;
import src.model.comics.ComicBook;

public class CSVExportTest {
    @Test
    public void testExport() throws IOException {
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

         Export testExport = new CSVExport("test");
         testExport.exportCollection(test);
    }
}
