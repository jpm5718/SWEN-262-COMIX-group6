package src;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import com.opencsv.CSVReaderHeaderAware;

public class ComicCSVReader {
    private CSVReaderHeaderAware csvreader;

    public ComicCSVReader(String file) throws Exception{
        csvreader = new CSVReaderHeaderAware(new FileReader(file));
    }

    public ArrayList<String> getNextComicInfo() throws Exception{
        
        Map<String, String> data = csvreader.readMap();

        /*Since using readMap() method, instantoate all data fields */

        String series = data.get("Series");
        String issue = data.get("Issue");
        String title = data.get("Full Title");
        String var_desc = data.get("Variant Descrition");
        String publisher = data.get("Publisher");
        String release_date = data.get("Release Date");
        String format = data.get("Format");
        String added_date = data.get("Added Date");
        String creators = data.get("Creators");

        ArrayList<String> datalist = new ArrayList<>();
        
        datalist.add(series);
        datalist.add(issue);
        datalist.add(title);
        datalist.add(var_desc);
        datalist.add(publisher);
        datalist.add(release_date);
        datalist.add(format);
        datalist.add(added_date);
        datalist.add(creators);
        
        return datalist;
    }
}
