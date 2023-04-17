package src.persistance;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.model.collections.Collection;
import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import src.model.comics.Comic;
import src.model.comics.ComicBook;

/*
 * Adapter class dedicated specifically to working between
 * java classes and JSON files, converting both JSON files to java objects
 * as well as converting JSON files into java classes
 * 
 * @author Anthony MacKay
 * 
 */


public class ComicJsonAdapter implements ComicAdapter {
    String filename;
    public ComicJsonAdapter(String filename) {
        filename = "data/" + filename + ".json";
    }
    @Override
    public void exportAsFormat(Collection dataCollection) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(dataCollection);
            FileWriter newWriter = new FileWriter(this.filename);
            newWriter.write(jsonString);
            newWriter.close();
        } catch (IOException e) {
            System.out.println("error occured");
            e.printStackTrace();
        }

    }
    @Override
    public ComicCollection importToFormat() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Comic[] comicList = mapper.readValue(new File(filename), ComicBook[].class);
            ComicCollection newCollection = new DatabaseCollection();
            for (Comic comic : comicList) {
                newCollection.addComic(comic);
            }
            return newCollection;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        

    }
    
}

