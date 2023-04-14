package src.view;

import java.util.Map;
import java.util.Scanner;

import src.model.collections.PersonalCollection;
import src.model.users.Auth;
import src.model.users.User;


/**
 * This beefy class handles a majority of the PTUI commands.
 * It reads in input from terminal, parses it, and then performs
 * certain actions
 * @author James McGuire
 */
public class UserInterface {
    //reference to current user
    private static User user = Auth.getCurrentUser();
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * This method is called when the user wants to manage 
     * their personal collection. It will have option to 
     * add to, remove from, and modify their collection. It
     * will also have options to grade existing comics
     */
    public static void personalCollectionHandler(){
        System.out.println("\nPersonal Collection Options" +
                           "\n\t1) View Collections" +
                           "\n\t2) Add New Collection" + 
                           "\n\t3) Remove an Exisiting Collection" +
                           "\n\n\t0) Quit");
        int choice = scanner.nextInt();
        switch(choice){
            case 0: break;

            //viewing existing collections
            case 1: System.out.println("The names of your collections are:");
                    Map<String, PersonalCollection> collections = user.getCollections();
                    for(String key : collections.keySet()){
                        System.out.println("\t" + key);
                    }
                    personalCollectionHandler();
                    break;

            //add a new collection
            case 2: System.out.println("What do you want to name this new Collection?");
                    scanner.nextLine(); //consume nextLine character
                    String newname = scanner.nextLine();
                    user.addPersonalCollection(newname);
                    personalCollectionHandler();
                    break;
            
            //remove a collection
            case 3: System.out.println("What is the name of the collection you would like to remove?");
                    scanner.nextLine(); //consume character
                    String removename = scanner.nextLine();
                    user.removePersonalCollection(removename);
                    personalCollectionHandler();
                    break;
        }
    }

    public static void run(){
        System.out.println("Choose a command from below:"+
            "\n\t1) Search the COMIX database"+ 
            "\n\t2) Manage Personal Collection");
        int choice = scanner.nextInt();

        switch(choice){
            case 1: //search handler
            case 2: personalCollectionHandler();
                    break;
        }
    }
}
