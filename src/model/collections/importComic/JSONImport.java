package src.model.collections.importComic;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.collections.PersonalCollection;
import src.model.comics.ComicBook;

public class JSONImport implements Import {
    private FileReader reader;
    private String fileDest;
    private File file;
    public JSONImport (String filename) throws IOException {
        this.fileDest = filename + ".json";
        this.file = new File(fileDest);
        if (!(file.exists())) {
            this.reader = new FileReader(fileDest);
        } else {
            file.delete();
            this.file = new File(fileDest);
            this.reader = new FileReader(fileDest);
        }
        System.out.println("Importer Created!");
    }

    public ComicCollection importCollection(int type) {
        try {
            ComicCollection collection;
            if (type == 1) {
                collection = new DatabaseCollection();
            } else if (type == 2) {
                collection = new PersonalCollection(fileDest);
            } else {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            ComicBook[] comics = mapper.readValue(this.file, ComicBook[].class);
            for (ComicBook comic : comics) {
                collection.addComic(comic);
            }
            return collection;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
