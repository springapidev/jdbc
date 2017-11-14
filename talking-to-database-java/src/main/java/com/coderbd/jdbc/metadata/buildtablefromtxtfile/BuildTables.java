package com.coderbd.jdbc.metadata.buildtablefromtxtfile;

/**
 * **********************************************************************
 * This program expects to find a file with the name SQLStatements.txt in the
 * same directory as the .class file for the program. This file should contain
 * the SQL statements to be executed. The SQLStatements.txt file included with
 * this program contains SQL that will create tables in the technical_library
 * Access database that should initially be empty, and already registered as an
 * ODBC database.
************************************************************************
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class BuildTables {

    public static void main(String[] args) {
        try {
            String user = "root";
            String password = "1234";
            String url = "jdbc:mysql://localhost:3306/swing";
            String driver = "com.mysql.jdbc.Driver";

            FileInputStream fis = new FileInputStream("E:\\git\\jdbc\\talking-to-database-java\\src\\main\\java\\com\\coderbd\\jdbc\\metadata\\buildtablefromtxtfile\\SQLStatements.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String SQLStatement = null;
            while ((SQLStatement = reader.readLine()) != null) {
                statement.executeUpdate(SQLStatement);
                System.out.println(SQLStatement);
            }
        } catch (FileNotFoundException cnfe) {
            System.err.println("SQL statements to create tables and their contents must be in/n"
                    + "a file with the name SQLStatements.txt in the same directory as this program file./n"
                    + "SQL statement must be one to a line.");
            System.err.println("File SQLStatements.txt does not exist. Terminating...");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
