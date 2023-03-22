package src;

import java.util.Scanner;

/**
 * This is the PTUI class. It is what is called when the app is launched
 * Provides option for the user to select depending on what action the
 * user wants to execute
 * 
 * @author James McGuire 
 */

public class PTUI {
    /*Stores the menu choice of the user */
    private int choice;
    
    Scanner input = new Scanner(System.in);

    /**
     * Prompts the user
     * Reads in the data the user inputs and directs it to the right subsystem
     */
    public void run(){
        System.out.println("\nWelcome to COMIX! Please select an option below to continue...");
        System.out.println("\n1) Search the database");
        System.out.println("\n2) Personal collection options");
        
        choice = input.nextInt();

        switch(choice){
            case 1:
                break;
            case 2:
                break;
        }

    }
}
