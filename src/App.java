package src;

import src.persistance.ComicBookFileDAO;

public class App{
    public static void main(String[] args) throws Exception {
        ComicBookFileDAO dao = new ComicBookFileDAO("data/comics.csv");
        dao.storeComics();
        System.out.println("JSON comic file created and seeded");
    }
}