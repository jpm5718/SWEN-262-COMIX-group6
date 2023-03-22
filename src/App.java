package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.*;
import java.util.Scanner;

/**
 * This is the class that runs the app. It contains various methods that
 * connect it to the postgres server and read in the comic books
 * from comics.csv and puts them into our database
 * 
 * @author James McGuire
 */
public class App{
    /*stores the url to connect with database */
    private final String url = "jdbc:postgresql://localhost/group6";

    /*stores user associated with database */
    private final String user = "group6";

    /*stores password of database to be used when connecting */
    private final String password = "group6";

    /*this method connects our project with our postrgres database */
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

    /*this method takes the data from the csv file and reads it into a table in our database */
    private void processComic() throws FileNotFoundException, SQLException{
        int count = 0;
        /*the complete line read in from the csv file */
        String line;

        /*the line after it has been split and stored in a string array */
        String[] splitline;

        /*Scanner object to read csv file */
        Scanner scanner = new Scanner(new File("data\\comics.csv"));

        /*this is the sql statement without the data inputted.
         * will be used when committing data to the table.
         */
        final String SQL_INSERT_STATEMENT = "INSERT INTO comics" +
        " (series, issue, title, var_desc, publisher, release_date, format, added_date, creators)" + 
        " (?, ?, ?, ? , ?, ?, ?, ?, ?);";
        
        scanner.nextLine(); //skips first three lines of csv file
        scanner.nextLine(); // ""
        scanner.nextLine(); // ""

        /*For each line in the csv file, it is split, each piece of data is stored, and then 
         * it is thrown into the correct cell in our database comics table
         */
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            splitline = line.split(","); //next few lines store each field in the line to its respective variable
            String series = splitline[0];
            String issue = splitline[1];
            String title = splitline[2];
            String description = splitline[3];
            String publisher = splitline[4];
            String release = splitline[5];
            String format = splitline[6];
            String dateadded = splitline[7];
            String creators = splitline[8];

            try{
                Connection conn = connect();
                PreparedStatement statement = conn.prepareStatement(SQL_INSERT_STATEMENT);

                statement.setString(1, series);  //throws correct piece of data into sql insert statement
                statement.setString(2, issue);
                statement.setString(3, title);
                statement.setString(4, description);
                statement.setString(5, publisher);
                statement.setString(6, release);
                statement.setString(7, format);
                statement.setString(8, dateadded);
                statement.setString(9, creators);

                statement.addBatch(); //

                count++;
                // execute every 100 rows or less
                if (count % 100 == 0 || !scanner.hasNextLine()) {
                    statement.executeBatch();
                }

            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.connect();
        app.processComic();
        PTUI ptui = new PTUI();
        ptui.run();
    }
}