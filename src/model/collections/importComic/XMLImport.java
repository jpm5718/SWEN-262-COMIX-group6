package src.model.collections.importComic;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;

public class XMLImport implements Import {
    private FileReader reader;
    private String fileDest;
    private File file;

    public XMLImport(String filename) throws IOException {
        this.fileDest = filename + ".xml";
        this.file = new File(fileDest);
        if (!(file.exists())) {
            this.reader = new FileReader(fileDest);
        } else {
            file.delete();
            this.reader = new FileReader(fileDest);
        }
        System.out.println("Exporter Created!");
    }
    public ComicCollection importCollection() {
        XmlMapper mapper = new XmlMapper();
        ComicCollection collection;
        try {
            collection = mapper.readValue(file, DatabaseCollection.class);
            return collection;
        } catch (StreamReadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabindException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
    }
}
