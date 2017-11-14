package com.coderbd.jdbc.metadata.resultSetMetaData;

import com.coderbd.jdbc.metadata.conn.MySqlDbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajail Islam
 */
public class DataRetrieveService {

    static Connection conn = MySqlDbConnection.getConnection();

    public static void getResultSetMetaData() {
     

        try {
           
            PreparedStatement stmt = conn.prepareStatement("select *from student");

            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println("Total columns: " + rsmd.getColumnCount());
            System.out.println("Column Name of 1st column: " + rsmd.getColumnName(1));
            System.out.println("Column Type Name of 1st column: " + rsmd.getColumnTypeName(1));
              System.out.println("Table name: " + rsmd.getTableName(1));
        } catch (SQLException ex) {
            Logger.getLogger(DataRetrieveService.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    public static void main(String[] args) {
        getResultSetMetaData();
    }
}

/*
Java ResultSetMetaData Interface

The metadata means data about data i.e. we can get further information from the data.

If you have to get metadata of a table like total number of column, column name, column type etc. , ResultSetMetaData interface is useful because it provides methods to get metadata from the ResultSet object.
Commonly used methods of ResultSetMetaData interface
Method	Description
public int getColumnCount()throws SQLException	it returns the total number of columns in the ResultSet object.
public String getColumnName(int index)throws SQLException	it returns the column name of the specified column index.
public String getColumnTypeName(int index)throws SQLException	it returns the column type name for the specified index.
public String getTableName(int index)throws SQLException	it returns the table name for the specified column index.
*/
