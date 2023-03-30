package src.persistance;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import src.model.ComicBook;

public class ComicCSVReader {
    private String file;
    List<ComicBook> comicbooks = new ArrayList<>();

    public ComicCSVReader(String filename){
        this.file = filename;
    }

    public List<ComicBook> parseComics() throws Exception{
        CSVReader reader = new CSVReader(new FileReader(file));  
        String[] line;
        
        while((line = reader.readNext()) != null){
            ComicBook comic = new ComicBook(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8]);
            comicbooks.add(comic);
        }
        reader.close();

        return comicbooks;
    }
}
