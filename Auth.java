import java.util.Scanner;

public class Auth {
    Scanner scan = new Scanner (System.in);
    Boolean guest = true;
    public String createUser(String username, String password) {

        return username;
    }

    public void logIn() {
        System.out.println("Username: ");
        String username = scan.nextLine();
        System.out.println("Password: ");
        String password = scan.nextLine();
        guest = false;
    }

    public void signIn() {
        System.out.println("Type your Username: ");
        String username = scan.nextLine();
        System.out.println("Type your Password: ");
        String password = scan.nextLine();
    }

    public void run() {
        System.out.println("Choose the command\n1 - Login\n2 - Sign in\n3 - Guest mode");
        if(scan.nextInt() == 1) {
            logIn();
        } else if (scan.nextInt() == 2) {
            signIn();
        }
    }
}
