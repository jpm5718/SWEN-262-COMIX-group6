package src.persistance;

import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.opencsv.CSVReader;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.comics.Comic;
import src.model.comics.ComicBook;

/**
 * This class uses the OpenCSV library to read in the data from the csv file 
 * and then store it in an array list to be processed by the FileDAO
 * 
 * @author James McGuire
 */
public class ComicCSVReader {
    /**the file path of the csv file */
    private String file;

    /**
     * Constructor so DOA can use the parser
     * 
     * @param filename file path of the csv file to be read in
     */
    public ComicCSVReader(String filename){
        this.file = "data/" + filename + ".csv";
    }

    DatabaseCollection database = new DatabaseCollection();

    /**
     * Creates a csv reader that reads in data line by line, and creates a 
     * ComicBook object. It then adds that object into the comicbooks ArrayList
     * 
     * @return the comicbooks ArrayList
     * @throws Exception 
     */
    public DatabaseCollection parseComics() throws Exception{
        CSVReader reader = new CSVReader(new FileReader(file));  
        String[] line;
        
        //skip first three lines of the csv file
        reader.readNext();
        reader.readNext();
        reader.readNext();

        int id = 0;
        while((line = reader.readNext()) != null){
            Queue<String> data = new LinkedList<>(Arrays.asList(line));
            data.add(String.valueOf(++id));
            Comic comic = new ComicBook(data);
            database.addComic(comic);
        }
        reader.close();

        return database;
    }
}
