package com.coderbd.jdbc.create;

import com.coderbd.jdbc.connections.OracleDBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajaul Islam
 */
public class TestCreateTable {
    //static Connection conn = MySqlDbConnection.getConnection();
     static Connection conn = OracleDBConnection.getConnection("xe", "hr", "hr");
    public static void main(String[] args) {
        String sql = "CREATE TABLE studentbd " +
                   "(id INTEGER not NULL, " +
                   " student_name VARCHAR(255), " +                   
                   " PRIMARY KEY ( id ))"; 
        try {
            PreparedStatement pstm=conn.prepareStatement(sql);
            int i=pstm.executeUpdate();
            System.out.println(i+ "table has been created successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(TestCreateTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
