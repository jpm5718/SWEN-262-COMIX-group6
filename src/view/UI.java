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
import src.model.comics.GradedComic;
import src.model.comics.SignedComic;
import src.model.command.AddComic;
import src.model.command.AuthenticateComic;
import src.model.command.Command;
import src.model.command.GradeComic;
import src.model.command.RemoveComic;
import src.model.command.SignComic;
import src.model.command.SlabComic;
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

    public static void signIn() {
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
                manageUser();
            } else {
                while (true) {
                    System.out.println("Invalid Password. Please enter again or type 1 to exit.\nInput:");
                    String input = scan.nextLine();
                    if (input.equals("1")) {
                    } else if (users.get(username).getPassword().equals(password)) {
                            System.out.println("\nLogged in as " + username);
                            currentUser = users.get(username);
                            loggedIn = true;
                            guest = false;
                            manageUser();
                    }
                }
            }
        } else {
            System.out.println("Invalid Input");
        }
    }

    public static void manageGuest() {
        // System.out.println("Welcome to Guest Mode!!\n\nChoose a command\n\t1) Browse Collection\n\t2) Search Collection\n\t3) Search Database");
        System.out.println("Welcome to Guest Mode!!\n\nChoose a command\n\t1) Search Database\n\t2) View Database");
        int choice = scanner.nextInt();
        switch (choice) {
            // close out of program
            case 1:
                searchCollectionHandler(database);
                break;
            case 2:
                for (Comic comic : database.getCollection()) {
                    System.out.println(comic.getTitle());
                }
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
                collection.setSearchStrategy(new SearchByComicType());
                results = collection.search(input, false);
                break;
            case 2:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByCreators());
                results = collection.search(input, false);
                break;
            case 3:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByDateAdded());
                results = collection.search(input, false);
                break;
            case 4:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByFormat());
                results = collection.search(input, false);
                break;
            case 5:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByGaps());
                results = collection.search(input, false);
                break;
            case 6:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByIssue());
                results = collection.search(input, false);
                break;
            case 7:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByPublisher());
                results = collection.search(input, false);
                break;
            case 8:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByReleaseDate());
                results = collection.search(input, false);
                break;
            case 9:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByRuns());
                results = collection.search(input, false);
                break;
            case 10:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchBySeries());
                results = collection.search(input, false);
                break;
            case 11:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByTitle());
                results = collection.search(input, false);
                break;
            case 12:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByVarDesc());
                results = collection.search(input, false);
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
        users.put(username, user);
        manageUser();
    }

    public static void manageUser(){
        System.out.println("\nUser Options" +
                "\n\t1) View Personal Collection Options" +
                "\n\t2) INSERT" +
                "\n\t3) Logout" +
                "\n\t4) Quit");
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
            
            case 3:
                currentUser = null;
                guest = true;
                loggedIn = false;
                manageStart();

        }
    }

    public static void personalCollectionHandler() {
        System.out.println("\nPersonal Collection Options" +
                "\n\t1) Search Collection" +
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
                searchCollectionHandler(currentUser.getCollection());
                personalCollectionHandler();
                break;

            // comic book actions
            case 2:
                ComicBookHandler();
                break;
        }
    }

    public static void addFromDatabaseHandler() {
        System.out.println("Enter a Comic ID from below to add to your collection:");
        for (Comic comic : database.getCollection()) {
            System.out.println();
            System.out.println("Series: " + comic.getSeries());
            System.out.println("Issue Number: " + comic.getIssue());
            System.out.println("Story Title: " + comic.getSeries());
            System.out.println("ID: " + comic.getId());            
        }
        System.out.print("ID of Comic to add: ");
        int input = scan.nextInt();
        Comic choice = database.getComic(input);
        Command addComicCommand = new AddComic(choice, currentUser.getCollection());
        addComicCommand.execute();
        commandsToUndo.add(addComicCommand);
        System.out.println(choice.getTitle() + " has been added to " + currentUser.getCollection().getName());
    }

    public static void removeComicHandler() {
        System.out.println("Enter a Comic ID from below to remove from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            System.out.println();
            System.out.println("Series: " + comic.getSeries());
            System.out.println("Issue Number: " + comic.getIssue());
            System.out.println("Story Title: " + comic.getSeries());
            System.out.println("ID: " + comic.getId());            
        }
        System.out.print("ID of Comic to remove: ");
        int input = scan.nextInt();
        Comic choice = currentUser.getCollection().getComic(input);
        Command removeComicCommand = new RemoveComic(choice, currentUser.getCollection());
        removeComicCommand.execute();
        commandsToUndo.add(removeComicCommand);
    }

    public static void gradeComicHandler() {
        System.out.println("Enter a Comic ID from below to grade from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            System.out.println();
            System.out.println("Series: " + comic.getSeries());
            System.out.println("Issue Number: " + comic.getIssue());
            System.out.println("Story Title: " + comic.getSeries());
            System.out.println("ID: " + comic.getId());            
        }
        System.out.print("ID of Comic to grade: ");
        int input = scan.nextInt();
        Comic choice = currentUser.getCollection().getComic(input);
        System.out.print("Grade of the comic: ");
        input = scan.nextInt();
        Command gradeComicCommand = new GradeComic(choice, input, currentUser.getCollection());
        gradeComicCommand.execute();
        commandsToUndo.add(gradeComicCommand);
    }

    public static void slabComicHandler() {
        System.out.println("Enter a Comic ID from below to slab from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            if (comic instanceof GradedComic) {
                System.out.println();
                System.out.println("Series: " + comic.getSeries());
                System.out.println("Issue Number: " + comic.getIssue());
                System.out.println("Story Title: " + comic.getSeries());
                System.out.println("ID: " + comic.getId());    
            }
        }
        System.out.print("ID of Comic to slab: ");
        int input = scan.nextInt();
        Comic choice = currentUser.getCollection().getComic(input);
        if (choice instanceof GradedComic) {
            Command slabComicCommand = new SlabComic((GradedComic) choice, currentUser.getCollection());
            slabComicCommand.execute();
            commandsToUndo.add(slabComicCommand);
        } else {
            System.out.println("Invalid choice");
        }
    }

    public static void signComicHandler() {
        System.out.println("Enter a Comic ID from below to sign from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            System.out.println();
            System.out.println("Series: " + comic.getSeries());
            System.out.println("Issue Number: " + comic.getIssue());
            System.out.println("Story Title: " + comic.getSeries());
            System.out.println("ID: " + comic.getId());            
        }
        System.out.print("ID of Comic to sign: ");
        int input = scan.nextInt();
        Comic choice = currentUser.getCollection().getComic(input);
        Command signComicCommand = new SignComic(choice, currentUser.getCollection());
        signComicCommand.execute();
        commandsToUndo.add(signComicCommand);
    }

    public static void authenticateComicHandler() {
        System.out.println("Enter a Comic ID from below to authenticate from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            if (comic instanceof SignedComic) {
                System.out.println();
                System.out.println("Series: " + comic.getSeries());
                System.out.println("Issue Number: " + comic.getIssue());
                System.out.println("Story Title: " + comic.getSeries());
                System.out.println("ID: " + comic.getId());    
            }
        }
        System.out.print("ID of Comic to authenticate: ");
        int input = scan.nextInt();
        Comic choice = currentUser.getCollection().getComic(input);
        if (choice instanceof SignedComic) {
            Command authenticateComicCommand = new AuthenticateComic((SignedComic) choice, currentUser.getCollection());
            authenticateComicCommand.execute();
            commandsToUndo.add(authenticateComicCommand);
        } else {
            System.out.println("Invalid choice");
        }
    }

    public static void ComicBookHandler() {
        System.out.println("Choose one of the following actions:" +
                "\n\t1) Add a Comic Book (manually)" +
                "\n\t2) Add a Comic Book (from database)" +
                "\n\t3) Remove a Comic" +
                "\n\t4) Grade a Comic" +
                "\n\t5) Slab a Comic" +
                "\n\t6) Sign a Comic" +
                "\n\t7) Authenticate a Comic" +
                "\n\t8) Undo Action" +
                "\n\t9) Redo Action" +
                "\n\t10) Go Back");
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
                addFromDatabaseHandler();
                break;

            case 3:
                removeComicHandler();
                break;

            case 4:
                gradeComicHandler();
                break;

            case 5:
                slabComicHandler();
                break;
            
            case 6:
                signComicHandler();
                break;
            
            case 7:
                authenticateComicHandler();
                break;

            case 8:
                Command undoCommand = commandsToUndo.pop();
                undoCommand.undo();
                commandsToRedo.add(undoCommand);
                break;

            case 9:
                Command redoCommand = commandsToRedo.pop();
                redoCommand.redo();
                commandsToUndo.add(redoCommand);
                break;
            
            case 10:
                personalCollectionHandler();
                break;

        }
        ComicBookHandler();
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
            signIn();
        } else if (command == 2) {
            manageSignUp();
        
        } else if (command == 3) {
            guest = true;
            manageGuest();
        }
    }

    public static void main(String[] args) {

        manageStart();
    }
}
