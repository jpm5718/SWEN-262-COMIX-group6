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
        } else {
            file.delete();
            this.writer = new FileWriter(fileDest);
        }
        System.out.println("Exporter Created!");
    }

    @Override
    public void exportCollection(ComicCollection comics) throws IOException {
        for (Comic comic : comics.getCollection()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Series", comic.getSeries().toString());
            jsonObject.put("Issue", comic.getIssue().toString());
            jsonObject.put("Title", comic.getTitle().toString());
            jsonObject.put("Variant Description", comic.getVarDesc().toString());
            jsonObject.put("Release Date", comic.getReleaseDate().toString());
            jsonObject.put("Fomat", comic.getFormat().toString());
            jsonObject.put("Date Added", comic.getDateAdded().toString());
            jsonObject.put("Publisher", comic.getPublisher().toString());
            jsonObject.put("Creators", comic.getCreators().toString());
            
            writer.write(jsonObject.toJSONString());
        }
        writer.flush();
    }
    
}
