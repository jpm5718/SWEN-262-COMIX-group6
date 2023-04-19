package src;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import src.model.collections.PersonalCollection;
import src.model.comics.ComicBook;
import src.model.comics.Creators;
import src.model.comics.Publisher;
import src.model.users.User;

public class PTUI {
    private static ObjectMapper mapper = new ObjectMapper(); 
    private static String filename = "data/test.json";

    static PersonalCollection pc = new PersonalCollection("testPersonalCollection");
    static User testuser = new User("username", "password", pc);

    public static void saveToJson(){
        try{
            mapper.writeValue(new File(filename), testuser);     
        } catch(IOException e){System.out.println(e.getMessage());}
    }

    public static void loadFromJson(){
        try{
            User readInUser = mapper.readValue(new File(filename), User.class);
            System.out.println(readInUser.getUsername());
        } catch(IOException e){System.out.println(e.getMessage());}
    }

    
    public static void main(String[] args) throws Exception {
        pc.addComic(new ComicBook("series", "1", "title", "decs", "date1", "format",
            "date2", new Publisher("pub"), new Creators("creators"), 0, 0));
        saveToJson();
    }
}