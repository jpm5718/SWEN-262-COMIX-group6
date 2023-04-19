package src.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import com.fasterxml.jackson.databind.ObjectMapper;

import src.model.collections.ComicCollection;
import src.model.collections.PersonalCollection;
import src.model.comics.Comic;
import src.model.comics.ComicBook;
import src.model.command.AddComic;
import src.model.command.Command;
import src.model.users.User;
import src.persistance.ComicCSVReader;
import src.persistance.UserFileDAO;

public class UI {
    private static Scanner scan = new Scanner(System.in);
    private static UserFileDAO dao;

    static User currentUser;
    static PersonalCollection currenentCollection;

    static ComicCSVReader reader = new ComicCSVReader("data/comics.csv");
    static ComicCollection database;

    static Stack<Command> commandsToUndo = new Stack<Command>();
    static Stack<Command> commandsToRedo = new Stack<Command>();

    static{
        try{
            dao = new UserFileDAO("data/users.json", new ObjectMapper());
        } catch(IOException e){System.out.println(e.getMessage());}
    }

    public static boolean signIn() throws Exception{
        User[] users = dao.getUsers();
        
        System.out.print("Username: ");
        String username = scan.nextLine();
        System.out.print("Password: ");
        String password = scan.nextLine();

        for(User u : users){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                System.out.println("\n\tLogged in as " + u.getUsername());
                currentUser = u;
                currenentCollection = u.getCollection();
                return true;
            }
            else{
                System.out.println("Invalid username or password. Redirecting back to home screen.");
                manageStart();
            }
        }
        return false;
    }

    public static boolean signUp() throws IOException{
        System.out.print("Enter username: ");
        String newUsername = scan.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scan.nextLine();
        System.out.print("Enter collection name: ");
        String newCollName = scan.nextLine();

        User user = new User(newUsername, newPassword, new PersonalCollection(newCollName));
        dao.addUser(user);
        currentUser = user;
        currenentCollection = user.getCollection();

        return true;
    }

    public static void manageUser() throws Exception{
        System.out.println("\nUser Options" +
                "\n\t1) View Personal Collection Options" +
                "\n\t2) INSERT" +
                "\n\n\t0) Return to Main Screen" +
                "\n\t-1) Quit");
        int choice = scan.nextInt();
        switch (choice) {
            case 1: personalCollectionHandler();
                    break;
            case -1: System.exit(0);
                    break;
        }
    }

    public static void personalCollectionHandler() throws Exception {
        System.out.println("\nPersonal Collection Options" +
                "\n\t1) Search Collection" +
                "\n\t2) Comic Book Actions (add, remove, edit, etc.)" +
                "\n\n\t0) Return to Main Screen" +
                "\n\t-1) Quit");
        int choice = scan.nextInt();
        switch (choice) {
            // close out of program
            case -1: 
                System.exit(0);
                    break;

            // back to main screen
            case 0: manageUser();
                    break;

            // viewing existing collections
            case 1:
                //searchCollectionHandler(currentUser.getCollection());
                personalCollectionHandler();
                break;

            // comic book actions
            case 2:
                ComicBookHandler();
                break;
        }
    }

    public static void manageStart() throws Exception{
        boolean loggedin = false;
        
        System.out.println("Welcome to COMIX!!\n\nChoose a command\n\t1) Login\n\t2) Sign up\n\t3) Guest mode");
        int command = scan.nextInt();
        scan.nextLine(); // add this line to consume the newline character

        switch(command){
            case 1: loggedin = signIn();
                    if(loggedin){
                        manageUser();
                    }        
                    break;
            case 2: loggedin = signUp();
                    if(loggedin){
                        manageUser();
                    }
                    break;
        }
    }

    public static void ComicBookHandler() throws Exception{
        System.out.println("Choose one of the following actions:" +
                "\n\t1) Add a Comic Book to Your Personal Collection (manually)" +
                "\n\t2) Add a Comic Book to Your Personal Collection (from database)" +
                "\n\t3) Grade a Comic That is in Your Collection" +
                "\n\t4) Slab a Comic" +
                "\n\t5) Sign a Comic" +
                "\n\t6) Authenticate a Comic" +
                "\n\t7) Undo Action" +
                "\n\t8) Redo Action" +
                "\n\t9) Go Back");
        int choice = scan.nextInt();
        switch (choice) {
            // add a comic manually to a collection
            case 1:
                    scan.nextLine();
                    // get comic book info
                    System.out.println("Next, we must get the comic book information...\nWhat is the series title?");
                    String series = scan.nextLine();
                    System.out.println("And the issue number?");
                    String issue = scan.nextLine();
                    System.out.println("What is the full title?");
                    String title = scan.nextLine();
                    System.out.println("What is the description of the comic?");
                    String vardesc = scan.nextLine();
                    System.out.println("Who is the publisher of the book?");
                    String publisher = scan.nextLine();
                    System.out.println("When was it released (Month, (day), Year)?");
                    String releasedate = scan.nextLine();
                    System.out.println("What format is it? (comic, graphic novel, etc.)?");
                    String format = scan.nextLine();
                    System.out.println("What is today's date (to keep track of date added)?");
                    String dateadded = scan.nextLine();
                    System.out.println("Who are the creators of the comic book?");
                    String creators = scan.nextLine();

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
                    int currentnum = currenentCollection.getNumberOfIssues(); // gets current num of entries so proper id can be
                                                                    // calculated
                    data.add(String.valueOf(++currentnum));

                    // create new comic and add it
                    currenentCollection.addComic(data);
                    // Command addComicCommand = new AddComic(newcomic, currenentCollection);
                    // addComicCommand.execute();
                    // commandsToUndo.add(addComicCommand);
                    dao.save();
                    System.out.println("Comic has been added to " + currenentCollection.getName());
                    break;
            case 2:
                //addFromDatabaseHandler();
                break;

            case 3:
                System.out.println("What is the comic you would like to grade?");
                // Comic comic = new ComicBook();
                // System.out.println("What is the grade of the comic?");
                // String s = scan.nextLine();
                // int grade = Integer.parseInt(s);
                // collection.gradeComic(comic, grade);
                break;

            case 4:
                System.out.println("What comic are you slabbing?");
                // DecoratorStrategy slab = new SlabStrategy();
                // collection.setDecoratorStrategy(slab);
                // slab.decorate(comic);
                break;
            
            case 5:
                System.out.println("What comic are you slabbing?");
                // DecoratorStrategy slab = new SlabStrategy();
                // collection.setDecoratorStrategy(slab);
                // slab.decorate(comic);
                break;
            
            case 6:
                System.out.println("What comic are you slabbing?");
                // DecoratorStrategy slab = new SlabStrategy();
                // collection.setDecoratorStrategy(slab);
                // slab.decorate(comic);
                break;

            case 7:
                Command undoCommand = commandsToUndo.pop();
                undoCommand.undo();
                commandsToRedo.add(undoCommand);
                break;

            case 8:
                Command redoCommand = commandsToRedo.pop();
                redoCommand.redo();
                commandsToUndo.add(redoCommand);
                break;
            
            case 9:
                manageUser();
                break;

        }
        ComicBookHandler();
    }

    public static void main(String[] args) throws Exception{
        manageStart();
    }
}
