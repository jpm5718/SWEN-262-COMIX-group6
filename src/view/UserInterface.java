package src.view;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import src.model.collections.DatabaseCollection;
import src.model.collections.PersonalCollection;
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
                "\n\t1) View Collections" +
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
                System.out.println("The names of your collections are:");
                PersonalCollection collection = user.getCollection();
                System.out.println("\t" + collection.getName());
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
                "\n\t1) Add a Comic Book to One of Your Personal Collections (manually)" +
                "\n\t2) Grade a Comic That is in Your Collection" +
                "\n\t3) Slab a Comic" +
                "\n\t4) Sign a Comic" +
                "\n\t5) Authenticate a Comic");
        int choice = scanner.nextInt();
        switch (choice) {
            // add a comic manually to a collection
            case 1:
                    PersonalCollection collection = user.getCollection(); // gets right collection from
                                                                                            // the users p.c. map

                    // get comic book info
                    System.out.println("Next, we must get the comic book information...\nWhat is the series title?");
                    String series = scanner.nextLine();
                    System.out.println("And the issue number?");
                    String issue = scanner.nextLine();
                    System.out.println("What is the full title?");
                    String title = scanner.nextLine();
                    System.out.println("What is the description of the comic?");
                    String vardesc = scanner.nextLine();
                    System.out.println("Who is the publisher of the book?");
                    String publisher = scanner.nextLine();
                    System.out.println("When was it released (Month, (day), Year)?");
                    String releasedate = scanner.nextLine();
                    System.out.println("What format is it? (comic, graphic novel, etc.)?");
                    String format = scanner.nextLine();
                    System.out.println("What is today's date (to keep track of date added)?");
                    String dateadded = scanner.nextLine();
                    System.out.println("Who are the creators of the comic book?");
                    String creators = scanner.nextLine();

                    // create queue to pass thru constructor
                    Queue<String> data = new LinkedList<>();
                    data.add(series);
                    data.add(issue);
                    data.add(title);
                    data.add(vardesc);
                    data.add(publisher);
                    data.add(releasedate);
                    data.add(format);
                    data.add(dateadded);
                    data.add(creators);
                    int currentnum = collection.getNumberOfIssues(); // gets current num of entries so proper id can be
                                                                    // calculated
                    data.add(String.valueOf(++currentnum));

                    // create new comic and add it
                    Comic newcomic = new ComicBook(data);
                    collection.addComic(newcomic);
                    System.out.println(newcomic.getTitle() + " has been added to " + collection.getName());
                    break;

            case 2:
                System.out.println("What is the comic you would like to grade?");
                // Comic comic = new ComicBook();
                // System.out.println("What is the grade of the comic?");
                // String s = scanner.nextLine();
                // int grade = Integer.parseInt(s);
                // collection.gradeComic(comic, grade);
                // break;

            case 3:
                System.out.println("What comic are you slabbing?");
                // DecoratorStrategy slab = new SlabStrategy();
                // collection.setDecoratorStrategy(slab);
                // slab.decorate(comic);
        }
    }

    public void run() throws Exception {
        System.out.println("Choose a command from below:" +
                "\n\t1) Search the COMIX database" +
                "\n\t2) Manage Personal Collection");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: System.out.println(db.getComic(35).getSeries());
            case 2:
                personalCollectionHandler();
                break;
        }
    }
}