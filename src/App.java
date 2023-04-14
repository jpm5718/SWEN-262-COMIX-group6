package src;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.users.Auth;
import src.persistance.ComicCSVReader;
import src.view.UserInterface;

public class App{
    public static void main(String[] args) throws Exception {
        ComicCSVReader reader = new ComicCSVReader("data/comics.csv");
        ComicCollection database = new DatabaseCollection();
        database = reader.parseComics();

        System.out.println("JSON comic file created and seeded\n\n");
        Auth.run();
        UserInterface.run();
    }
}