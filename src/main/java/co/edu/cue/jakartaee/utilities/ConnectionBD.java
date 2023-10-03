package co.edu.cue.jakartaee.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
    private static String url="jdbc:mysql://localhost:8081/prove_db";
    private static String user="root";
    private static String password="cue";
    private static Connection connection;
    public static Connection getInstance() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url,user,password);
    }
}