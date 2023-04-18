package src.model.users;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.mindrot.jbcrypt.BCrypt;
import com.fasterxml.jackson.databind.ObjectMapper;

import src.model.collections.PersonalCollection;


public class Auth {
    Scanner scan = new Scanner(System.in);
    Boolean guest = true;
    Boolean loggedIn = false;
    private User currentUser;

    private static ObjectMapper mapper = new ObjectMapper();
    Map<String, User> users;

    public User createUser(String username, String password, String name) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        PersonalCollection collection = new PersonalCollection(name);
        User user = new User(username, hashedPassword, collection);
        return user;
    }

    public boolean load() throws Exception {
        users = new TreeMap<>();

        // load in users from json and store in array
        User[] usersArray = mapper.readValue(new File("data/users.json"), User[].class);

        // add existing to local treemap
        for (User user : usersArray) {
            users.put(user.getUsername(), user);
        }

        return true;
    }

    public User[] getUsersArray() {
        ArrayList<User> usersArrayList = new ArrayList<>(users.values());
        User[] userArray = new User[usersArrayList.size()];
        usersArrayList.toArray(userArray);
        return userArray;
    }

    public boolean save() throws Exception {
        User[] userArray = getUsersArray();
        mapper.writeValue(new File("data/users.json"), userArray);
        return true;
    }

    public void logIn() throws Exception {
        System.out.println("\nUsername: ");
        String username = scan.nextLine();
        System.out.println("\nPassword: ");
        String password = scan.nextLine();

        load();
        User[] userArray = getUsersArray();
        for(User user : userArray){
            String storedUsername = user.getUsername();
            String storedpw = user.getPassword();

            if(storedUsername.equals(username) && BCrypt.checkpw(password, storedpw)){
                System.out.println("\nLogged in as " + username);
                loggedIn = true;
                guest = false;
                currentUser = users.get(username);
            }
        }
    }

    public void signUp() throws Exception {
        // get info from user
        System.out.println("\nCreate your Username: ");
        String username = scan.nextLine();
        System.out.println("\nCreate your Password: ");
        String password = scan.nextLine();
        System.out.println("Create collection name: ");
        String collectionName = scan.nextLine();

        load(); // load existing
        User newUser = createUser(username, password, collectionName); // create new user
        users.put(newUser.getUsername(), newUser); // add it to map
        save(); // save to file

        loggedIn = true;
        guest = false;
        currentUser = newUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void run() throws Exception {
        System.out.println("Welcome to COMIX!!\n\nChoose a command\n\t1) Login\n\t2) Sign up\n\t3) Guest mode");
        int command = scan.nextInt();
        scan.nextLine(); // add this line to consume the newline character
        if (command == 1) {
            logIn();
        } else if (command == 2) {
            signUp();
        }
    }
}
