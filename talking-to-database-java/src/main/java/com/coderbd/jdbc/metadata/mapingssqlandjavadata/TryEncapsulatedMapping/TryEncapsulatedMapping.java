package com.coderbd.jdbc.metadata.mapingssqlandjavadata.TryEncapsulatedmapping;
import com.coderbd.jdbc.metadata.conn.MySqlDbConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TryEncapsulatedMapping {
  public static void main (String[] args) {
    TryEncapsulatedMapping SQLtoJavaExample;
    try {
      SQLtoJavaExample = new TryEncapsulatedMapping();
      SQLtoJavaExample.listAuthors();
    } catch(SQLException sqle) {
      System.err.println(sqle);
    } catch(ClassNotFoundException cnfe) {
      System.err.println(cnfe);
    }
  }
Connection connection;
  public TryEncapsulatedMapping() throws SQLException, 
                                         ClassNotFoundException {
   
    connection =MySqlDbConnection.getConnection();
  }

  public void listAuthors() throws SQLException {
    Author author;
    String query = "SELECT authid, lastname, firstname, address1,"+
                   "address2, city, state_prov, postcode, country,"+
                   "phone, fax, email FROM authors";

    Statement statement = connection.createStatement();
    ResultSet authors = statement.executeQuery(query);

    while(authors.next())
      System.out.println("\n" + Author.fromResults(authors));
    authors.close();
    connection.close();
  }

  
  
}
