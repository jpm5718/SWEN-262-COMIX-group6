package src;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.users.Auth;
import src.persistance.ComicCSVReader;

public class App{
    public static void main(String[] args) throws Exception {
        ComicCSVReader reader = new ComicCSVReader("data/comics.csv");
        ComicCollection database = new DatabaseCollection();
        database = reader.parseComics();

        System.out.println("JSON comic file created and seeded");
        Auth.run();
    }
}