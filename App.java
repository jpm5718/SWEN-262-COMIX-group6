import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App{
    private final String url = "jdbc:postgresql://localhost/group6";
    private final String user = "group6";
    private final String password = "group6";

    public Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to PostgreSQL successful.");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        App app = new App();
        app.connect();
        PTUI ptui = new PTUI();
        ptui.run();
    }
}