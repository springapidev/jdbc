package com.coderbd.jdbc.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J2EE-33
 */
public class OracleDBConnection {
     // private static final String DRIVERNAME = "oracle.jdbc.driver.OracleDriver";
    private static final String HOST = "jdbc:oracle:thin:@localhost";
    private static final String PORT = "1521";
    private static Connection connection;
    private static String url = HOST + ":" + PORT + ":";

    public static Connection getConnection(String dbname,
            String dbUserName, String dbPass) {
        try {
            connection = DriverManager.getConnection(url + dbname, dbUserName, dbPass);
            System.out.println("::::Connected::::");
        } catch (SQLException ex) {
            Logger.getLogger(OracleDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    } 
}
