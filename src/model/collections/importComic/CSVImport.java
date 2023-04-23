package src.model.collections.importComic;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.opencsv.CSVReader;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.collections.PersonalCollection;
import src.model.comics.Comic;
import src.model.comics.ComicBook;

public class CSVImport implements Import {
    private CSVReader reader;
    private String fileDest;

    public CSVImport(String filename) throws IOException {
        this.fileDest = filename + ".csv";
        File file = new File(fileDest);
        if(!(file.exists())) {
            this.reader = new CSVReader(new FileReader(fileDest));
        } else {
            file.delete();
            this.reader = new CSVReader(new FileReader(fileDest));
        }
        System.out.println("Importer Created!");
    }

    @Override
    public ComicCollection importCollection(int type) {
        ComicCollection collection;
        if (type == 1) {
            collection = new DatabaseCollection();
        } else if (type == 2) {
            collection = new PersonalCollection(fileDest);
        } else {
            return null;
        }
        

        try {
            reader.readNext();
            String[] line;
            while((line = reader.readNext()) != null) {
                Queue<String> attributes = new LinkedList<String>();
                for (int i = 0; i < line.length; i++) {
                    attributes.add(line[i].toString());
                }
                ComicBook book = new ComicBook(attributes);
                collection.addComic(book);
            }
            return collection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
