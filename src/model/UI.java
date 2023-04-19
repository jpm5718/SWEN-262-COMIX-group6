package src.model;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import src.model.collections.PersonalCollection;
import src.model.users.User;
import src.persistance.UserFileDAO;

public class UI {
    private static Scanner scan = new Scanner(System.in);
    private static UserFileDAO dao;

    static User currentUser;
    static PersonalCollection currenentCollection;

    static{
        try{
            dao = new UserFileDAO("data/users.json", new ObjectMapper());
        } catch(IOException e){System.out.println(e.getMessage());}
    }

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

    public static void signUp() throws IOException{
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
    }

    public static void manageUser(){
        
    }

    public static void manageStart() throws IOException{
        System.out.println("Welcome to COMIX!!\n\nChoose a command\n\t1) Login\n\t2) Sign up\n\t3) Guest mode");
        int command = scan.nextInt();
        scan.nextLine(); // add this line to consume the newline character

        switch(command){
            case 1: signIn();
                    manageUser();
                    break;
            case 2: signUp();
                    break;
        }
    }

    public static void main(String[] args) throws IOException{
        manageStart();
    }
}
