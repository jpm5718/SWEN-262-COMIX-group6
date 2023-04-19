package src;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import src.model.collections.PersonalCollection;
import src.model.comics.ComicBook;
import src.model.comics.Creators;
import src.model.comics.Publisher;
import src.model.users.User;
import src.persistance.UserFileDAO;

public class PTUI {
    // ObjectMapper mapper = new ObjectMapper(); 
    // String filename = "data/test.json";

    // public void saveToJson(){
    //     try{
    //         mapper.writeValue(new File(filename), testuser);     
    //     } catch(IOException e){System.out.println(e.getMessage());}
    // }

    // public void loadFromJson(){
    //     try{
    //         User[] readInUsers = mapper.readValue(new File(filename), User[].class);
    //         System.out.println(readInUsers[0].getUsername());
    //     } catch(IOException e){System.out.println(e.getMessage());}
    // }
 
    
    public static void main(String[] args) throws Exception {
        UserFileDAO dao = new UserFileDAO("data/test.json", new ObjectMapper());

        PersonalCollection pc = new PersonalCollection("jamesCollection");
        PersonalCollection pc2 = new PersonalCollection("danCollection");

        User user1 = new User("james", "james", pc);
        User user2 = new User("dan", "dan", pc2);

        dao.addUser(user1);
        dao.addUser(user2);

        User[] users = dao.getUsers();
        for(User u : users){
            System.out.println(u.getCollection().getName());
        }
    }
}