package sample;

import java.sql.*;

public class sqlcnx {
    public static Connection cnx() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection cnn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite3\\GUI\\DataBase");
            return cnn;
        } catch (Exception e) {
            System.out.println("errerur cnx");
            return null;

        }
    }

}
