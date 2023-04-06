package src.persistance;

import java.io.FileWriter;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import src.model.collections.Collection;

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
    @Override
    public Collection importToFormat() {
        return null;
    }
    
}
