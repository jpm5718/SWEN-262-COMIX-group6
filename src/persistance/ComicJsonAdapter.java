package src.persistance;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.model.collections.Collection;
import java.io.FileWriter;
import java.io.IOException;

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
    public Collection importToFormat() {
        return null;
    }
    
}
