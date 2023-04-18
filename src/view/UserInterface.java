package src.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import src.model.collections.DatabaseCollection;
import src.model.collections.PersonalCollection;
import src.model.collections.search.SearchBySeries;
import src.model.collections.search.SearchStrategy;
import src.model.comics.Comic;
import src.model.comics.ComicBook;
import src.model.users.Auth;
import src.model.users.User;
import src.persistance.ComicCsvAdapter;

/**
 * This beefy class handles a majority of the PTUI commands.
 * It reads in input from terminal, parses it, and then performs
 * certain actions
 *
 * @author James McGuire
 */
public class UserInterface {
    Auth auth = new Auth();
    // reference to current user
    private User user = auth.getCurrentUser();
    //private PersonalCollection userPersonalCollection = user.getCollection();
    private Scanner scanner = new Scanner(System.in);
    private ComicCsvAdapter csvreader = new ComicCsvAdapter("data/comics.csv");
    private DatabaseCollection db = csvreader.importToFormat();

    /**
     * This method is called when the user wants to manage
     * their personal collection. It will have option to
     * add to, remove from, and modify their collection. It
     * will also have options to grade existing comics
     */
    public void personalCollectionHandler() throws Exception {
        System.out.println("\nPersonal Collection Options" +
                "\n\t1) View Collection" +
                "\n\t2) Comic Book Actions (add, remove, edit, etc.)" +
                "\n\n\t0) Return to Main Screen" +
                "\n\t-1) Quit");
        int choice = scanner.nextInt();
        switch (choice) {
            // close out of program
            case -1:
                try{
                    auth.save();
                } catch(Exception e) { }
                System.exit(choice);
                break;

            // back to main screen
            case 0:
                run();
                break;

            // viewing existing collections
            case 1:
                // System.out.println("The names of your collections are:");
                // PersonalCollection collection = user.getCollection();
                // System.out.println("\t" + collection.getName());
                personalCollectionHandler();
                break;

            // comic book actions
            case 2:
                ComicBookHandler();
                break;
        }
    }

    public void ComicBookHandler() {
        System.out.println("Choose one of the following actions:" +
                "\n\t1) Add a Comic Book to Your Personal Collection (manually)" +
                "\n\t2) Grade a Comic That is in Your Collection" +
                "\n\t3) Slab a Comic" +
                "\n\t4) Sign a Comic" +
                "\n\t5) Authenticate a Comic");
        int choice = scanner.nextInt();
        switch (choice) {
            
        }
    }

    public void databaseHandler(){
        System.out.println("\nHow would you like to search the database by? " +
                        "\n\t1)By Series" + 
                        "\n\t2)By Issue" +
                        "\n\t3)By Title" +
                        "\n\t4)By Format" +
                        "\n\t5)By Publisher" +
                        "\n\t6)By Description" +
                        "\n\t7)By Release Date");
        int searchtypechoice = scanner.nextInt();

        scanner.nextLine(); //consume nextline char

        switch(searchtypechoice){
            case 1: System.out.println("What series are you searching for? ");
                    String seriesTerm = scanner.nextLine();
                    SearchStrategy series = new SearchBySeries();
                    db.setSearchStrategy(series);
                    ArrayList<Comic> seriesResult = db.search(seriesTerm, false);
                    for(Comic entry : seriesResult){
                        System.out.println(entry.toString());
                    }

                    System.out.println("\nOf these results, enter the id of the coimic you would like to add to your collection!");
                    int userComicChoice = scanner.nextInt();
                    Comic toBeAdded = db.getComic(userComicChoice);
                    user.getCollection().addComic(toBeAdded);
        }
    }

    public void run() throws Exception {
        System.out.println("Choose a command from below:" +
                "\n\t1) Search the COMIX database" +
                "\n\t2) Manage Personal Collection");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: 
                databaseHandler();
                break;
            case 2:
                personalCollectionHandler();
                break;
        }
    }
}