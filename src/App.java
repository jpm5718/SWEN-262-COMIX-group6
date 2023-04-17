package src;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.command.AddComic;
import src.model.command.Command;
import src.model.users.Auth;
import src.model.users.User;
import src.persistance.ComicCSVReader;

public class App{
    public static void main(String[] args) throws Exception {
        ComicCSVReader reader = new ComicCSVReader("data/comics.csv");
        ComicCollection database = new DatabaseCollection();
        database = reader.parseComics();

        System.out.println("JSON comic file created and seeded");
        // Auth.run();
        User mike = new User("mike", "mike");
        mike.addPersonalCollection("big");
        System.out.println(mike.getPersonalCollections().get("big"));
        Command add = new AddComic(database.getCollection().get(1), mike.getPersonalCollections().get("big"));
        add.execute();
        System.out.println(mike.getPersonalCollections().get("big").getCollection().keySet().toString());
        add.undo();
        System.out.println(mike.getPersonalCollections().get("big").getCollection().keySet().toString());
        add.redo();
        System.out.println(mike.getPersonalCollections().get("big").getCollection().keySet().toString());


    }
}