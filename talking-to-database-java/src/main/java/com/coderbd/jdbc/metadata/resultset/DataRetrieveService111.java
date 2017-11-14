package com.coderbd.jdbc.metadata.resultset;

import com.coderbd.jdbc.metadata.statement.*;
import com.coderbd.jdbc.metadata.conn.MySqlDbConnection;
import com.coderbd.jdbc.metadata.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajail Islam
 */
public class DataRetrieveService111 {

    static Connection conn = MySqlDbConnection.getConnection();

    public static Student getStudent() {
        Student s = null;

        try {

            PreparedStatement stmt = conn.prepareStatement("select *from student");

            ResultSet rs = stmt.executeQuery();
            //getting the record of 3rd row  
            rs.last();
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataRetrieveService111.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public static void main(String[] args) {
        getStudent();
    }

}
/*
public boolean last():	is used to move the cursor to the last row in result set object.
*/