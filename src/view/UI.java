package src.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import src.model.collections.ComicCollection;
import src.model.collections.DatabaseCollection;
import src.model.collections.PersonalCollection;
import src.model.collections.search.SearchByComicType;
import src.model.collections.search.SearchByCreators;
import src.model.collections.search.SearchByDateAdded;
import src.model.collections.search.SearchByFormat;
import src.model.collections.search.SearchByGaps;
import src.model.collections.search.SearchByIssue;
import src.model.collections.search.SearchByPublisher;
import src.model.collections.search.SearchByReleaseDate;
import src.model.collections.search.SearchByRuns;
import src.model.collections.search.SearchBySeries;
import src.model.collections.search.SearchByTitle;
import src.model.collections.search.SearchByVarDesc;
import src.model.collections.search.SearchStrategy;
import src.model.comics.Comic;
import src.model.comics.ComicBook;
import src.model.command.AddComic;
import src.model.command.Command;
import src.model.users.User;
import src.persistance.ComicCSVReader;

public class UI {

    static Scanner scan = new Scanner(System.in);
    static Scanner scanner = new Scanner(System.in);
    static Boolean guest = true;
    static Boolean loggedIn = false;
    static User currentUser;
    static HashMap<String, User> users = new HashMap<>();
    static PersonalCollection guestCollection;

    static ComicCSVReader reader = new ComicCSVReader("data/comics.csv");
    static ComicCollection database;

    static Stack<Command> commandsToUndo = new Stack<Command>();
    static Stack<Command> commandsToRedo = new Stack<Command>();

    public static boolean signIn() {
        System.out.println("\nUsername: ");
        String username = scan.nextLine();
        System.out.println("\nPassword: ");
        String password = scan.nextLine();

        if (users.keySet().contains(username)) {
            if (users.get(username).getPassword().equals(password)) {
                System.out.println("\nLogged in as " + username);
                currentUser = users.get(username);
                loggedIn = true;
                guest = false;
                return true;
            } else {
                while (true) {
                    System.out.println("Invalid Password. Please enter again or type 1 to exit.\nInput:");
                    String input = scan.nextLine();
                    if (input.equals("1")) {
                        return false;
                    } else if (users.get(username).getPassword().equals(password)) {
                            System.out.println("\nLogged in as " + username);
                            currentUser = users.get(username);
                            loggedIn = true;
                            guest = false;
                            return true;
                    }
                }
            }
        } else {
            System.out.println("Invalid Input");
            return false;
        }
    }

    public static void manageGuest() {
        // System.out.println("Welcome to Guest Mode!!\n\nChoose a command\n\t1) Browse Collection\n\t2) Search Collection\n\t3) Search Database");
        System.out.println("Welcome to Guest Mode!!\n\nChoose a command\n\t1) Search Database");
        int choice = scanner.nextInt();
        switch (choice) {
            // close out of program
            case 1:
                searchCollectionHandler(database);
                break;
        }


    }

    public static void searchCollectionHandler(ComicCollection collection) {
        String input = "";
        ArrayList<Comic> results = null;
        System.out.println("Choose one of the following actions:" +
                "\n\t1) Search by Comic Type" +
                "\n\t2) Search by Creators" +
                "\n\t3) Search by DateAdded" +
                "\n\t4) Search by Format" +
                "\n\t5) Search by Gaps" +
                "\n\t6) Search by Issue" +
                "\n\t7) Search by Publisher" +
                "\n\t8) Search by Release Date" +
                "\n\t9) Search by Runs" +
                "\n\t10) Search by Series" +
                "\n\t11) Search by Title" +
                "\n\t12) Search by Var Description");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByComicType());
                results = database.search(input, false);
                break;
            case 2:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByCreators());
                results = database.search(input, false);
                break;
            case 3:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByDateAdded());
                results = database.search(input, false);
                break;
            case 4:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByFormat());
                results = database.search(input, false);
                break;
            case 5:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByGaps());
                results = database.search(input, false);
                break;
            case 6:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByIssue());
                results = database.search(input, false);
                break;
            case 7:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByPublisher());
                results = database.search(input, false);
                break;
            case 8:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByReleaseDate());
                results = database.search(input, false);
                break;
            case 9:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByRuns());
                results = database.search(input, false);
                break;
            case 10:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchBySeries());
                results = database.search(input, false);
                break;
            case 11:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByTitle());
                results = database.search(input, false);
                break;
            case 12:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                database.setSearchStrategy(new SearchByVarDesc());
                results = database.search(input, false);
                break;
        }
        for (Comic comic : results) {
            System.out.println(comic.getTitle());
        }
    }

    public static void manageSignUp() {
        System.out.print("Username: ");
        String username = scan.nextLine();
        System.out.print("Password: ");
        String password = scan.nextLine();
        System.out.print("Collection Name: ");
        String collName = scan.nextLine();
        User user = new User(username, password, new PersonalCollection(collName));
        currentUser = user;
    }

    public static void manageUser(){
        System.out.println("\nUser Options" +
                "\n\t1) View Personal Collection Options" +
                "\n\t2) Comic Book Actions (add, remove, edit, etc.)" +
                "\n\n\t0) Return to Main Screen" +
                "\n\t-1) Quit");
        int choice = scanner.nextInt();
        switch (choice) {
            // close out of program
            case 1:
                try {
                    personalCollectionHandler();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;

        }
    }

    public static void personalCollectionHandler() throws Exception {
        System.out.println("\nPersonal Collection Options" +
                "\n\t1) View Collections" +
                "\n\t2) Comic Book Actions (add, remove, edit, etc.)" +
                "\n\n\t0) Return to Main Screen" +
                "\n\t-1) Quit");
        int choice = scanner.nextInt();
        switch (choice) {
            // close out of program
            case -1:
                break;

            // back to main screen
            case 0:
                break;

            // viewing existing collections
            case 1:
                System.out.print("The names of your collection is:");
                PersonalCollection collection = currentUser.getCollection();
                System.out.println("\t" + collection.getName());
                personalCollectionHandler();
                break;

            // comic book actions
            case 2:
                ComicBookHandler();
                break;
        }
    }

    public static void ComicBookHandler() {
        System.out.println("Choose one of the following actions:" +
                "\n\t1) Add a Comic Book to One of Your Personal Collections (manually)" +
                "\n\t2) Grade a Comic That is in Your Collection" +
                "\n\t3) Slab a Comic" +
                "\n\t4) Sign a Comic" +
                "\n\t5) Authenticate a Comic" +
                "\n\t6) Undo Action" +
                "\n\t7) Redo Action");
        int choice = scanner.nextInt();
        switch (choice) {
            // add a comic manually to a collection
            case 1:
                    PersonalCollection collection = currentUser.getCollection(); // gets right collection from
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
                    Command addComicCommand = new AddComic(newcomic, collection);
                    addComicCommand.execute();
                    commandsToUndo.add(addComicCommand);
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

            case 6:
                Command undoCommand = commandsToUndo.pop();
                undoCommand.undo();
                commandsToRedo.add(undoCommand);

            case 7:
                Command redoCommand = commandsToRedo.pop();
                redoCommand.redo();
                commandsToUndo.add(redoCommand);

        }
    }

    public static void manageStart() {
        try {
            database = reader.parseComics();
        } catch (Exception e) {
            System.out.println("Error Loading");
        }
        System.out.println("Welcome to COMIX!!\n\nChoose a command\n\t1) Login\n\t2) Sign up\n\t3) Guest mode");
        int command = scan.nextInt();
        scan.nextLine(); // add this line to consume the newline character
        if (command == 1) {
            boolean signedIn = signIn();
            if (signedIn==false) {
                manageStart();
                System.out.println(currentUser);
            }
        } else if (command == 2) {
            manageSignUp();
            manageUser();
        
        } else if (command == 3) {
            guest = true;
            manageGuest();
        }
    }

    public static void main(String[] args) {

        manageStart();
    }
}
