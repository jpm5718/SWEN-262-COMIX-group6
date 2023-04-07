
//package src.persistance;
//
//import java.io.FileWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import src.model.comics.Comic;
//
//public class ComicBookFileDAO implements ComicBookDAO{
//    /**the name of the file that comics are being read in from */
//    private String csv_filename;
//
//    private String jsonfile = "data/comics.json";
//
//    /**the list containing all the comic objects, used to convert objects into json objects */
//    private List<Comic> comics = new ArrayList<>();
//
//    /**used to serialize and deserialize objects to/from json formatting */
//    private ObjectMapper mapper = new ObjectMapper();
//
//    public ComicBookFileDAO(String csv_filename){
//        this.csv_filename = csv_filename;
//    }
//
//    public String[] getComics(){
//        return null;
//    }
//
//    public void storeComics() throws Exception{
//        ComicCSVReader csvreader = new ComicCSVReader(csv_filename);
//        comics = csvreader.parseComics();
//        mapper.writeValue(new FileWriter(jsonfile), comics);
//    }
//}

