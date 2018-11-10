package com.coderbd.jdbc.insert;

import com.coderbd.jdbc.connections.MySqlDbConnection;
import com.coderbd.jdbc.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertIntoTable {

    private static Connection conn = MySqlDbConnection.getConnection();

    public static void insertData() {
        String sql = "insert into division(id, name) values(1,'Dhaka')";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("data Inserted");
        } catch (SQLException ex) {
            Logger.getLogger(InsertIntoTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insertData2(int divId, String n) {
        String sql = "insert into division(id, name) values(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, divId);
            ps.setString(2, n);
            ps.executeUpdate();

            System.out.println("data Inserted");
        } catch (SQLException ex) {
            Logger.getLogger(InsertIntoTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
      public static void insertData3(Student s) {
        String sql = "insert into division(id, name) values(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, s.getId());
            ps.setString(2, s.getStudentName());
            ps.executeUpdate();

            System.out.println("data Inserted");
        } catch (SQLException ex) {
            Logger.getLogger(InsertIntoTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public static ResultSet getDataById(int id) {
        ResultSet rs = null;
        String sql = "select * from division where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(InsertIntoTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

}
