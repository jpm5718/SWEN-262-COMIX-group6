package src.model.collections.exportComic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

import src.model.collections.ComicCollection;
import src.model.comics.Comic;

public class JSONExport implements Export {

    private FileWriter writer;
    private String fileDest;

    public JSONExport(String filename) throws IOException {
        this.fileDest = filename + ".json";
        File file = new File(fileDest);
        if (!(file.exists())) {
            this.writer = new FileWriter(fileDest);
            writer.write("[");
        } else {
            file.delete();
            this.writer = new FileWriter(fileDest);
            writer.write("[");
        }
        System.out.println("Exporter Created!");
    }

    @Override
    public void exportCollection(ComicCollection comics) throws IOException {
        for (Comic comic : comics.getCollection()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("series", comic.getSeries().toString());
            jsonObject.put("issue", comic.getIssue().toString());
            jsonObject.put("title", comic.getTitle().toString());
            jsonObject.put("varDesc", comic.getVarDesc().toString());
            jsonObject.put("releaseDate", comic.getReleaseDate().toString());
            jsonObject.put("format", comic.getFormat().toString());
            jsonObject.put("dateAdded", comic.getDateAdded().toString());
            jsonObject.put("publisher", comic.getPublisher().toString());
            jsonObject.put("creators", comic.getCreators().toString());
            
            writer.write(jsonObject.toJSONString());
            writer.write("]");
        }
        writer.flush();
    }
    
}
