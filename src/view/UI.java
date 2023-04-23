package src.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import com.fasterxml.jackson.databind.ObjectMapper;

import src.model.collections.ComicCollection;
import src.model.collections.PersonalCollection;
import src.model.collections.editComic.CreatorsEditor;
import src.model.collections.editComic.DateAddedEditor;
import src.model.collections.editComic.EditPublisher;
import src.model.collections.editComic.FormatEditor;
import src.model.collections.editComic.IssueEditor;
import src.model.collections.editComic.ReleaseDateEditor;
import src.model.collections.editComic.SeriesEditor;
import src.model.collections.editComic.VarDescEditor;
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
import src.model.collections.sort.SortByDateAdded;
import src.model.collections.sort.SortByFormat;
import src.model.collections.sort.SortByID;
import src.model.collections.sort.SortByIssue;
import src.model.collections.sort.SortByReleaseDate;
import src.model.collections.sort.SortBySeries;
import src.model.collections.sort.SortByTitle;
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
import src.persistance.UserFileDAO;

public class UI {

    static Scanner scan = new Scanner(System.in);
    static Scanner scanner = new Scanner(System.in);

    static User currentUser;

    static UserFileDAO dao;

    static{
        try{
            dao = new UserFileDAO("data/users.json", new ObjectMapper());
        } catch(IOException e){System.out.println(e.getMessage());}
    }

    static ComicCSVReader reader = new ComicCSVReader("comics");
    static ComicCollection database;

    static Stack<Command> commandsToUndo = new Stack<Command>();
    static Stack<Command> commandsToRedo = new Stack<Command>();

    public static boolean signIn() throws IOException{
        User[] users = dao.getUsers();

        System.out.print("Username: ");
        String username = scan.nextLine();
        System.out.print("Password: ");
        String password = scan.nextLine();

        for(User u : users){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                System.out.println("\n\tLogged in as " + u.getUsername());
                currentUser = u;
                return true;
            }
            else{
                System.out.println("Invalid username or password. Redirecting back to home screen.");
                manageStart();
            }
        }
        return false;
    }

    public static void manageGuest() throws IOException{
        // System.out.println("Welcome to Guest Mode!!\n\nChoose a command\n\t1) Browse Collection\n\t2) Search Collection\n\t3) Search Database");
        System.out.println("Welcome to Guest Mode!!\n\nChoose a command\n\t1) Search Database\n\t2) View Database");
        int choice = -1;
        try {
            String commandString = scan.nextLine();
            choice = Integer.parseInt(commandString);
            if (choice != 1 && choice != 2) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            manageGuest();
        }
        switch (choice) {
            // close out of program
            case 1:
                searchCollectionHandler(database);
                break;
            case 2:
                for (Comic comic : database.getCollection()) {
                    System.out.println(comic);
                }
        }


    }

    public static void searchCollectionHandler(ComicCollection collection) throws IOException{
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
                "\n\t12) Search by Var Description" +
                "\n\t13) Quit");

        int choice = -1;
        try {
            String commandString = scan.nextLine();
            choice = Integer.parseInt(commandString);
            if (choice < 1 || choice > 13) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("\nError: Please enter the number of a command from the list aboven\n");
            searchCollectionHandler(collection);
        }

        if (choice == 13) {
                System.out.println("Goodbye!");
                System.exit(0);
        }

        boolean exactMatch = false;
        if (choice != 5 && choice != 9) {
                System.out.println("Which match results would you like to view?" +
                    "\n\t1) Exact Matches" +
                    "\n\t2) Partial Matches");
            int matchChoice = 0;
            try {
                String commandString = scan.nextLine();
                matchChoice = Integer.parseInt(commandString);
                if (matchChoice != 1 && matchChoice != 2) {
                    throw new Exception();
                } else if (matchChoice == 1) { exactMatch = true;}
            } catch (Exception e) {
                System.out.println("\nError: Please enter the number of a command from the list aboven\n");
                searchCollectionHandler(collection);
            }
        }

        switch (choice) {
            case 1:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByComicType());
                results = collection.search(input, exactMatch);
                break;
            case 2:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByCreators());
                results = collection.search(input, exactMatch);
                break;
            case 3:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByDateAdded());
                results = collection.search(input, exactMatch);
                break;
            case 4:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByFormat());
                results = collection.search(input, exactMatch);
                break;
            case 5:
                collection.setSearchStrategy(new SearchByGaps());
                results = collection.search(null, exactMatch);
                break;
            case 6:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByIssue());
                results = collection.search(input, exactMatch);
                break;
            case 7:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByPublisher());
                results = collection.search(input, exactMatch);
                break;
            case 8:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByReleaseDate());
                results = collection.search(input, exactMatch);
                break;
            case 9:
                collection.setSearchStrategy(new SearchByRuns());
                results = collection.search(null, exactMatch);
                break;
            case 10:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchBySeries());
                results = collection.search(input, exactMatch);
                break;
            case 11:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByTitle());
                results = collection.search(input, exactMatch);
                break;
            case 12:
                System.out.print("Search Term: ");
                input = scan.nextLine();
                collection.setSearchStrategy(new SearchByVarDesc());
                results = collection.search(input, exactMatch);
                break;
        }
        for (Comic comic : results) {
            System.out.println(comic);
            System.out.println();
        }
        System.out.print("\nEnter the id of the comic you would like to add to \n" + 
        "or enter 0 to go back. ");
        int idChoice = scanner.nextInt();
        if(idChoice != 0){
            Comic choiceComic = database.getComic(idChoice);
            Command addComicCommand = new AddComic(choiceComic, currentUser.getCollection());
            addComicCommand.execute();
            commandsToUndo.add(addComicCommand);
            System.out.println(choiceComic.getTitle() + " has been added to " + currentUser.getCollection().getName());
            dao.save();
        }
        else    
            searchCollectionHandler(collection);
    }

    public static boolean manageSignUp() throws IOException{
        System.out.print("Enter username: ");
        String newUsername = scan.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scan.nextLine();
        System.out.print("Enter collection name: ");
        String newCollName = scan.nextLine();

        User user = new User(newUsername, newPassword, new PersonalCollection(newCollName));
        dao.addUser(user);
        currentUser = user;
        dao.save();

        return true;
    }

    public static void manageUser() throws IOException{
        System.out.println("\nUser Options" +
                "\n\t1) View Personal Collection Options" +
                "\n\t2) Search Database" +
                "\n\t3) Logout" +
                "\n\t4) Quit");
        int choice = -1;
        try {
            String commandString = scan.nextLine();
            choice = Integer.parseInt(commandString);
            if (choice < 1 || choice > 4) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            manageUser();
        }
        switch (choice) {
            // close out of program
            case 1:
                try {
                    personalCollectionHandler();
                } catch (Exception e) {

                }
                break;
            case 2:
                searchCollectionHandler(database);
                manageUser();
                break;
            case 3:
                currentUser = null;
                manageStart();
                break;
            case 4:
                System.out.println("Goodbye!");
                System.exit(0);
                break;

        }
    }

    public static void sortCollectionHandler(ComicCollection collection) {
        ArrayList<Comic> results = null;
        System.out.println("Choose one of the following actions:" +
                "\n\t1) Sort by Date Added" +
                "\n\t2) Sort by Format" +
                "\n\t3) Sort by ID" +
                "\n\t4) Sort by Issue" +
                "\n\t5) Sort by Release Date" +
                "\n\t6) Sort by Series" +
                "\n\t7) Sort by Title" +
                "\n\t8) Go Back" +
                "\n\t9) Quit");
        int choice = -1;
        try {
            String commandString = scan.nextLine();
            choice = Integer.parseInt(commandString);
            if (choice < 1 || choice > 9) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            sortCollectionHandler(collection);
        }

        switch (choice) {
            case 1:
                collection.setSortStrategy(new SortByDateAdded());
                results = collection.sort();
                break;
            case 2:
                collection.setSortStrategy(new SortByFormat());
                results = collection.sort();
                break;
            case 3:
                collection.setSortStrategy(new SortByID());
                results = collection.sort();
                break;
            case 4:
                collection.setSortStrategy(new SortByIssue());
                results = collection.sort();
                break;
            case 5:
                collection.setSortStrategy(new SortByReleaseDate());
                results = collection.sort();
                break;
            case 6:
                collection.setSortStrategy(new SortBySeries());
                results = collection.sort();
                break;
            case 7:
                collection.setSortStrategy(new SortByTitle());
                results = collection.sort();
                break;
            case 8:
                try {
                    personalCollectionHandler();
                } catch (IOException e) {
                }

            case 9:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
        for (Comic comic : results) {
            System.out.println(comic);
            System.out.println();
        }
        try {
            personalCollectionHandler();
        } catch (IOException e) {
        }
    }

    public static void personalCollectionHandler() throws IOException{
        System.out.println("\nPersonal Collection Options" +
                "\n\t1) Search Collection" +
                "\n\t2) Sort Collection" +
                "\n\t3) Comic Book Actions (add, remove, edit, etc.)" +
                "\n\t4) Go Back" +
                "\n\t5) Quit");
        int choice = -1;
        try {
            String commandString = scan.nextLine();
            choice = Integer.parseInt(commandString);
            if (choice < 1 || choice > 5) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            personalCollectionHandler();
        }
        switch (choice) {

            // viewing existing collections
            case 1:
                searchCollectionHandler(currentUser.getCollection());
                personalCollectionHandler();
                break;

            case 2:
                sortCollectionHandler(currentUser.getCollection());
                break;

            // comic book actions
            case 3:
                ComicBookHandler();
                break;

            case 4:
                manageUser();
                break;

            case 5:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
    }

    public static void addManuallyHandler() throws IOException{
        PersonalCollection collection = currentUser.getCollection();
        scanner.nextLine();

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
        System.out.println("What ID would you like to assign to the comic book? (must be over 14301)");
        String id;
        while (true) {
            id = scanner.nextLine();
            if (Integer.parseInt(id) <= 14301) {
                System.out.println("Error: invalid id, please enter an id that is greater than 14301");;
            } else { break; }
        }

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
        data.add(id);

        // create new comic and add it
        Comic newcomic = new ComicBook(data);
        Command addComicCommand = new AddComic(newcomic, collection);
        addComicCommand.execute();
        commandsToUndo.add(addComicCommand);
        System.out.println(newcomic.getTitle() + " has been added to " + collection.getName());
        dao.save();
    }

    public static void addFromDatabaseHandler() throws IOException {
        System.out.println("Enter a Comic ID from below to add to your collection:");
        // for (Comic comic : database.getCollection()) {
        //     System.out.println(comic);
        // }
        try {
            System.out.print("ID of Comic to add: ");
            String commandString = scan.nextLine();
            int input = Integer.parseInt(commandString);
            Comic choice = database.getComic(input);
            Command addComicCommand = new AddComic(choice, currentUser.getCollection());
            addComicCommand.execute();
            commandsToUndo.add(addComicCommand);
            System.out.println(choice.getTitle() + " has been added to " + currentUser.getCollection().getName());
            dao.save();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            addFromDatabaseHandler();
        }
    }

    public static void removeComicHandler() throws IOException{
        System.out.println("Enter a Comic ID from below to remove from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            System.out.println(comic);
        }
        try {
            System.out.print("ID of Comic to remove: ");
            String commandString = scan.nextLine();
            int input = Integer.parseInt(commandString);
            Comic choice = currentUser.getCollection().getComic(input);
            Command removeComicCommand = new RemoveComic(choice, currentUser.getCollection());
            removeComicCommand.execute();
            commandsToUndo.add(removeComicCommand);
            dao.save();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            removeComicHandler();
        }
    }

    public static void editComicHandler() throws IOException {
        PersonalCollection collection = currentUser.getCollection();
        System.out.println("Enter a Comic ID from below to edit from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            System.out.println(comic);
        }
        try {
            System.out.print("ID of Comic to edit: ");
            String commandString = scan.nextLine();
            int input = Integer.parseInt(commandString);
            Comic choice = currentUser.getCollection().getComic(input);
            System.out.println("Which field of the Comic would you like to modify? " +
                "\n\t1) Creators" +
                "\n\t2) Date Added" +
                "\n\t3) Publisher" +
                "\n\t4) Format" +
                "\n\t5) Issue" +
                "\n\t6) Release Date" +
                "\n\t7) Series" +
                "\n\t8) Description");
            commandString = scan.nextLine();
            input = Integer.parseInt(commandString);
            switch (input) {
                case 1:
                    collection.setEditStrategy(new CreatorsEditor());
                    break;
                case 2:
                    collection.setEditStrategy(new DateAddedEditor());
                    break;
                case 3:
                    collection.setEditStrategy(new EditPublisher());
                    break;
                case 4:
                    collection.setEditStrategy(new FormatEditor());
                    break;
                case 5:
                    collection.setEditStrategy(new IssueEditor());
                    break;
                case 6:
                    collection.setEditStrategy(new ReleaseDateEditor());
                    break;
                case 7:
                    collection.setEditStrategy(new SeriesEditor());
                    break;
                case 8:
                    collection.setEditStrategy(new VarDescEditor());
                    break;
            }

            System.out.print("Replacement for field: ");
            String line = scan.nextLine();
            collection.editComic(choice, line);
            dao.save();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            editComicHandler();
        }
    }

    public static void gradeComicHandler() throws IOException{
        System.out.println("Enter a Comic ID from below to grade from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            System.out.println(comic);
        }
        try {
            System.out.print("ID of Comic to grade: ");
            String commandString = scan.nextLine();
            int input = Integer.parseInt(commandString);
            Comic choice = currentUser.getCollection().getComic(input);
            System.out.print("Grade of the comic: ");
            input = scan.nextInt();
            Command gradeComicCommand = new GradeComic(choice, input, currentUser.getCollection());
            gradeComicCommand.execute();
            commandsToUndo.add(gradeComicCommand);
            dao.save();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            gradeComicHandler();
        }
    }

    public static void slabComicHandler() throws IOException{
        System.out.println("Enter a Comic ID from below to slab from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            if (comic instanceof GradedComic) {
                System.out.println(comic);
            }
        }
        try {
            System.out.print("ID of Comic to slab: ");
            String commandString = scan.nextLine();
            int input = Integer.parseInt(commandString);
            Comic choice = currentUser.getCollection().getComic(input);
            if (choice instanceof GradedComic) {
                Command slabComicCommand = new SlabComic((GradedComic) choice, currentUser.getCollection());
                slabComicCommand.execute();
                commandsToUndo.add(slabComicCommand);
            } else {
                System.out.println("Error: comic must be graded before slabbing");
            }
            dao.save();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            slabComicHandler();
        }
    }

    public static void signComicHandler() throws IOException{
        System.out.println("Enter a Comic ID from below to sign from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            System.out.println(comic);
        }
        try {
            System.out.print("ID of Comic to sign: ");
            String commandString = scan.nextLine();
            int input = Integer.parseInt(commandString);            Comic choice = currentUser.getCollection().getComic(input);
            Command signComicCommand = new SignComic(choice, currentUser.getCollection());
            signComicCommand.execute();
            commandsToUndo.add(signComicCommand);
            dao.save();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            signComicHandler();
        }
    }

    public static void authenticateComicHandler() throws IOException{
        System.out.println("Enter a Comic ID from below to authenticate from your collection:");
        for (Comic comic : currentUser.getCollection().getCollection()) {
            if (comic instanceof SignedComic) {
                System.out.println(comic);
            }
        }
        try {
            System.out.print("ID of Comic to authenticate: ");
            String commandString = scan.nextLine();
            int input = Integer.parseInt(commandString);            Comic choice = currentUser.getCollection().getComic(input);
            if (choice instanceof SignedComic) {
                Command authenticateComicCommand = new AuthenticateComic((SignedComic) choice, currentUser.getCollection());
                authenticateComicCommand.execute();
                commandsToUndo.add(authenticateComicCommand);
            } else {
                System.out.println("Invalid choice");
            }
            dao.save();
        } catch (Exception e) {
            System.out.println("Invalid Input");
            authenticateComicHandler();
        }
    }

    public static void ComicBookHandler() throws IOException{
        System.out.println("Choose one of the following actions:" +
                "\n\t1) Add a Comic Book (manually)" +
                "\n\t2) Add a Comic Book (from database)" +
                "\n\t3) Remove a Comic" +
                "\n\t4) Edit a Comic" +
                "\n\t5) Grade a Comic" +
                "\n\t6) Slab a Comic" +
                "\n\t7) Sign a Comic" +
                "\n\t8) Authenticate a Comic" +
                "\n\t9) Undo Action" +
                "\n\t10) Redo Action" +
                "\n\t11) Go Back");
        int choice = -1;
        try {
            String commandString = scan.nextLine();
            choice = Integer.parseInt(commandString);
            if (choice < 1 || choice > 11) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            ComicBookHandler();
        }
        switch (choice) {
            // add a comic manually to a collection
            case 1:
                addManuallyHandler();
                break;
            case 2:
                addFromDatabaseHandler();
                break;

            case 3:
                removeComicHandler();
                break;

            case 4:
                editComicHandler();
                break;

            case 5:
                gradeComicHandler();
                break;

            case 6:
                slabComicHandler();
                break;

            case 7:
                signComicHandler();
                break;

            case 8:
                authenticateComicHandler();
                break;

            case 9:
                Command undoCommand = commandsToUndo.pop();
                undoCommand.undo();
                commandsToRedo.add(undoCommand);
                dao.save();
                break;

            case 10:
                Command redoCommand = commandsToRedo.pop();
                redoCommand.redo();
                commandsToUndo.add(redoCommand);
                dao.save();
                break;

            case 11:
                personalCollectionHandler();
                break;

        }
        ComicBookHandler();
    }

    public static void manageStart() throws IOException {
        try {
            database = reader.parseComics();
        } catch (Exception e) {
            System.out.println("Error Loading");
        }
        manageWelcome();
    }

    public static void manageWelcome() {
        System.out.println("Welcome to COMIX!!\n\nChoose a command\n\t1) Login\n\t2) Sign up\n\t3) Guest mode");
        try {
            String commandString = scan.nextLine();
            int command = Integer.parseInt(commandString);
            // scan.next(); // add this line to consume the newline character
            if (command == 1) {
                signIn();
                manageUser();
            } else if (command == 2) {
                manageSignUp();
                manageUser();
            } else if (command == 3) {
                manageGuest();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            manageWelcome();
        }
    }

    public static void main(String[] args) throws IOException{
        manageStart();
    }
}
