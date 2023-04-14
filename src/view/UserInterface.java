package src.view;

import src.model.users.Auth;
import src.model.users.User;

public class UserInterface {
    private static User user = Auth.getCurrentUser();
    
    public static void run(){
        System.out.println("Current user = " + user.getUsername());
    }
}
