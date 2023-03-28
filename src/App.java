package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import src.model.ComicBook;

/**
 * This is the class that runs the app. It contains various methods that
 * connect it to the postgres server and read in the comic books
 * from comics.csv and puts them into our database
 * 
 * @author James McGuire
 */
public class App {
    /* stores the url to connect with database */
    private final String url = "jdbc:postgresql://localhost/group6";

    /* stores user associated with database */
    private final String user = "group6";

    /* stores password of database to be used when connecting */
    private final String password = "group6";

    /* this method connects our project with our postrgres database */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void createTables() {
        try (Connection conn = connect(); Statement statement = conn.createStatement(0, 0);) {
            String sql_create_query = "DROP TABLE IF EXISTS comics;" +
                    "CREATE TABLE comics" +
                    "(id SERIAL PRIMARY KEY," +
                    " series TEXT," +
                    " issue TEXT," +
                    "title TEXT," +
                    "var_desc TEXT, " +
                    "publisher TEXT," +
                    "release_date TEXT," +
                    "format TEXT," +
                    "added_date TEXT, " +
                    "creators TEXT);";
            statement.executeUpdate(sql_create_query);
            System.out.println("Created comics table...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertComics(String file) throws Exception {
        try {
            ComicCSVReader ccr = new ComicCSVReader(file);
            ccr.getNextComicInfo();
            ccr.getNextComicInfo();

            while (ccr.getNextComicInfo() != null) {
                ComicBook comic = new ComicBook();
                
                ArrayList<String> al = ccr.getNextComicInfo();

                comic.parseData(al, comic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.connect();
        app.createTables();
        app.insertComics("data/comics.csv");
        PTUI ptui = new PTUI();
        ptui.run();
    }
}