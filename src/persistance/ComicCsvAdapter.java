package src.persistance;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import src.model.collections.Collection;
import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.comics.Comic;
import src.model.comics.ComicBook;

/*
 * Concrete Adapter Class specifically for CSV file format.
 * This adapter class should be able to apply the methods of
 * Adapter Interface effectively in order to better handle
 * files for CSV format in regards to both Importing CSV files and exporting
 * Java objects to CSV using StatefulBeanToCSV library methods
 * 
 * @author Anthony MacKay
 * 
 */


public class ComicCsvAdapter implements ComicAdapter {
    String filename;
    public ComicCsvAdapter(String filename) {
        filename = "data/" + filename + ".csv";
    }

    /*
     * Converts a java comic book collection to a json file.
     * Params: dataCollection - collection object being converted
     * Returns: None
     */
    @Override
    public void exportAsFormat(Collection dataCollection) {
        // TODO Auto-generated method stub
        String destination = this.filename;

        try {
            FileWriter writer = new FileWriter(destination);

            ColumnPositionMappingStrategy<Collection> mapping = new ColumnPositionMappingStrategy<Collection>();
            mapping.setType(Collection.class);
            String[] columns = new String[] {"id", "series", "issue", "fullTitle", "varDesc", "publisher", "releaseDate", "format", "dateAdded", "creators", "value"};
            mapping.setColumnMapping(columns);
            StatefulBeanToCsvBuilder<Collection> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv beanWriter = builder.withMappingStrategy(mapping).build();
            beanWriter.write(dataCollection);
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Converts information from a CSV file into a collection object with an array of comic books
     * Params: None
     * Returns: newCollection - new collection object
     * 
     * NOTE: much of the code for reading csv files is converted from the old ComcisCSVReader class. Credit to James McGuire for original implementation
     */
    @Override
    public ComicCollection importToFormat() throws IOException {
        try {
            CSVReader reader = new CSVReader(new FileReader(filename));
            String[] line;
            //skip first three lines of the csv file
            reader.readNext();
            int id = 0;
            ComicCollection newCollection = new DatabaseCollection();
            while((line = reader.readNext()) != null){
                Queue<String> data = new LinkedList<>(Arrays.asList(line));
                data.add(String.valueOf(++id));
                Comic comic = new ComicBook(data);
                newCollection.addComic(comic);
            }
            reader.close();
            return newCollection;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }  
        
    }
    
}
