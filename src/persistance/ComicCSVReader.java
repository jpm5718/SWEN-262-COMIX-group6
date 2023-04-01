package src.persistance;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.opencsv.CSVReader;

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

    /**the array list that will be sent back to the file dao */
    List<Comic> comicbooks = new ArrayList<>();

    /**
     * Constructor so DOA can use the parser
     * 
     * @param filename file path of the csv file to be read in
     */
    public ComicCSVReader(String filename){
        this.file = filename;
    }

    /**
     * Creates a csv reader that reads in data line by line, and creates a 
     * ComicBook object. It then adds that object into the comicbooks ArrayList
     * 
     * @return the comicbooks ArrayList
     * @throws Exception 
     */
    public List<Comic> parseComics() throws Exception{
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
            comicbooks.add(comic);
        }
        reader.close();

        return comicbooks;
    }
}
