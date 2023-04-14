package src.model.users;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.mindrot.jbcrypt.BCrypt;

public class Auth {
    static Scanner scan = new Scanner(System.in);
    static Boolean guest = true;
    static Boolean loggedIn = false;
    private static User currentUser;

    public static User createUser(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, hashedPassword);

        return user;
    }

    public static void logIn() {
        System.out.println("\nUsername: ");
        String username = scan.nextLine();
        System.out.println("\nPassword: ");
        String password = scan.nextLine();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("data/users.json"));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray users = (JSONArray)jsonObject.get("users");
            Iterator iterator = users.iterator();
            while (iterator.hasNext() && !loggedIn) {
                JSONObject user = (JSONObject)iterator.next();
                String storedUsername = (String)user.get("Username");
                String storedHashedPassword = (String)user.get("Password");
                if (storedUsername.equals(username) && BCrypt.checkpw(password, storedHashedPassword)) {
                    System.out.println("\nLogged in as " + username);
                    loggedIn = true;
                    guest = false;
                    currentUser = new User(storedUsername, storedHashedPassword);
                }
            }
            if (!loggedIn) {
                System.out.println("\nInvalid username or password");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void signUp() {
        System.out.println("\nCreate your Username: ");
        String username = scan.nextLine();
        System.out.println("\nCreate your Password: ");
        String password = scan.nextLine();

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Read in the existing user data from the JSON file
        JSONParser parser = new JSONParser();
        JSONArray users = new JSONArray();
        try {
            Object obj = parser.parse(new FileReader("data/users.json"));
            JSONObject jsonObject = (JSONObject) obj;
            users = (JSONArray) jsonObject.get("users");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a new user object with the given username and hashed password
        JSONObject newUser = new JSONObject();
        newUser.put("Username", username);
        newUser.put("Password", hashedPassword);

        // Add the new user to the array of existing users
        users.add(newUser);

        // Write the updated user data back to the JSON file
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("users", users);
        try {
            FileWriter fileWriter = new FileWriter("data/users.json");
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
            loggedIn = true;
            guest = false;
            System.out.println("\nSigned Up successfully and logged in!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentUser = createUser(username, hashedPassword);
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void run() {
        System.out.println("Welcome to COMIX!!\n\nChoose a command\n\t1) Login\n\t2) Sign up\n\t3) Guest mode");
        int command = scan.nextInt();
        scan.nextLine(); // add this line to consume the newline character
        if(command == 1) {
            logIn();
        } else if (command == 2) {
            signUp();
        }
    }
}
