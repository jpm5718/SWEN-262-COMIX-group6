package src.model.collections.exportComic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;

import src.model.collections.ComicCollection;
import src.model.comics.Comic;

public class CSVExport implements Export {

    private CSVWriter writer;
    private String fileDest;

    public CSVExport(String filename) throws IOException {
        this.fileDest = filename + ".csv";
        File file = new File(fileDest);
        if(!(file.exists())) {
            this.writer = new CSVWriter(new FileWriter(fileDest));
        } else {
            file.delete();
            this.writer = new CSVWriter(new FileWriter(fileDest));
        }
        System.out.println("Exporter Created!");
    }

    @Override
    public void exportCollection(ComicCollection comics) throws IOException {
        String header[] = {"Series","Issue","Full Title","Variant Description","Publisher","Release Date","Format","Added Date","Creators","id"};
        writer.writeNext(header);
        for (Comic comic: comics.getCollection()) {
            String nextLine[] = {
                comic.getSeries().toString(),
                comic.getIssue().toString(),
                comic.getTitle().toString(),
                comic.getVarDesc().toString(), 
                comic.getPublisher().toString(), 
                comic.getReleaseDate().toString(), 
                comic.getFormat().toString(), 
                comic.getDateAdded().toString(), 
                comic.getCreators().toString(),
                String.valueOf(comic.getId())
            };
            writer.writeNext(nextLine);
        }
        writer.flush();
        /*
         * BufferedReader br = new BufferedReader(new FileReader(fileDest));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
         */
        //Verify that file works
        
    }
}
