package src;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.comics.GradedComic;
import src.model.command.AddComic;
import src.model.command.Command;
import src.model.command.GradeComic;
import src.model.command.RemoveComic;
import src.model.command.SignComic;
import src.model.command.SlabComic;
import src.model.users.Auth;
import src.model.users.User;
import src.persistance.ComicCSVReader;
import src.view.UserInterface;

public class App {
    public static void main(String[] args) throws Exception {
        ComicCSVReader reader = new ComicCSVReader("data/comics.csv");
        ComicCollection database = new DatabaseCollection();
        Auth auth = new Auth();
        UserInterface ui = new UserInterface();

        database = reader.parseComics();

        System.out.println("JSON comic file created and seeded");
        // Auth.run();
        User mike = new User("mike", "mike");
        mike.addPersonalCollection("big");
        System.out.println(mike.getPersonalCollections().get("big"));
        Command add = new AddComic(database.getCollection().get(1), mike.getPersonalCollections().get("big"));
        add.execute();
        System.out.println(mike.getPersonalCollections().get("big").getCollection().toString());
        Command remove = new RemoveComic(database.getCollection().get(1), mike.getPersonalCollections().get("big"));
        remove.execute();
        System.out.println(mike.getPersonalCollections().get("big").getCollection().toString());
        remove.undo();
        System.out.println(mike.getPersonalCollections().get("big").getCollection().toString());

        System.out.println(mike.getPersonalCollections().get("big").getCollection().get(0).getValue());
        GradeComic sign = new GradeComic(database.getCollection().get(1), 5, mike.getPersonalCollections().get("big"));
        sign.execute();
        System.out.println(mike.getPersonalCollections().get("big").getCollection().get(0).getValue());
        sign.undo();
        System.out.println(mike.getPersonalCollections().get("big").getCollection().get(0).getValue());
        sign.redo();
        System.out.println(mike.getPersonalCollections().get("big").getCollection().get(0).getValue());
        System.out.println(mike.getPersonalCollections().get("big").getCollection().toString());



    }
}