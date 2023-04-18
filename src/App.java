package src;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.users.Auth;
import src.persistance.ComicCSVReader;
import src.view.UserInterface;

public class App {
     public static void main(String[] args) throws Exception {
    //     ComicCSVReader reader = new ComicCSVReader("data/comics.csv");
    //     ComicCollection database = new DatabaseCollection();
       // database = reader.parseComics();
       Auth auth = new Auth();
       UserInterface ui = new UserInterface();

       auth.run();
       ui.run();

        //database = reader.parseComics();

        // System.out.println("JSON comic file created and seeded");
        // // Auth.run();
        // PersonalCollection collection = new PersonalCollection("big");
        // User mike = new User("mike", "mike", collection);
        // System.out.println(mike.getCollection());
        // Command add = new AddComic(database.getCollection().get(1), mike.getCollection());
        // add.execute();
        // System.out.println(mike.getCollection().getCollection().toString());
        // Command remove = new RemoveComic(database.getCollection().get(1), mike.getCollection());
        // remove.execute();
        // System.out.println(mike.getCollection().getCollection().toString());
        // remove.undo();
        // System.out.println(mike.getCollection().getCollection().toString());

        // System.out.println(mike.getCollection().getCollection().get(0).getValue());
        // GradeComic sign = new GradeComic(database.getCollection().get(1), 5, mike.getCollection());
        // sign.execute();
        // System.out.println(mike.getCollection().getCollection().get(0).getValue());
        // sign.undo();
        // System.out.println(mike.getCollection().getCollection().get(0).getValue());
        // sign.redo();
        // System.out.println(mike.getCollection().getCollection().get(0).getValue());
        // System.out.println(mike.getCollection().getCollection().toString());

    }
}