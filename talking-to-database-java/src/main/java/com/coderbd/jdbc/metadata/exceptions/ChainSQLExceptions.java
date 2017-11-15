package com.coderbd.jdbc.metadata.exceptions;
import com.coderbd.jdbc.metadata.conn.MySqlDbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChainSQLExceptions {
  public static void main(String[] args) {
    ChainSQLExceptions theApp = new ChainSQLExceptions();
    try {
      theApp.doQuery();               // Call the method that deals with the DB
    } catch(SQLException sqle) {      // Catch the exception thrown by the method
      do {                            // loop through each exception
        // do something with each exception
        System.err.println("Exception occurred:\nMessage: " + sqle.getMessage());
        System.err.println("SQL state: " + sqle.getSQLState());
        System.err.println("Vendor code: " + sqle.getErrorCode() +
                           "\n----------------");
      } while((sqle = sqle.getNextException()) != null);
    }
  }

  // Method to add an exception to a chain of SQLExceptions
  public void doQuery() throws SQLException {
  
    String theStatement = "SELECT lastname, firstname FROM autors";

    try {
   
      Connection connection = MySqlDbConnection.getConnection();
      Statement queryAuthors = connection.createStatement();
      ResultSet theResults = queryAuthors.executeQuery(theStatement);

      queryAuthors.close();
    }  catch(SQLException sqle) {
      SQLException generatedException = new SQLException(        // New exception
                                      "SQL operation cancelled", // Message
                                      "S1008",                   // SQL state
                                      0);                        // Vendor code
      generatedException.setNextException(sqle);       // Append the old exception
      throw generatedException;    }                   // and throw the chain
  }
}
