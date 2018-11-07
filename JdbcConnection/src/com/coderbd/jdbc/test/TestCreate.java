package com.coderbd.jdbc.test;

import com.coderbd.jdbc.create.CreateTableUsingMysql;
import com.coderbd.jdbc.insert.InsertIntoTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCreate {
    
    public static void main(String[] args) {
        try {
            //  CreateTableUsingMysql.createTable();
            //InsertIntoTable.insertData();
//        InsertIntoTable.insertData2(3, "Khulna");
//        InsertIntoTable.insertData2(4, "Sylhet");
//        InsertIntoTable.insertData2(5, "Chitagong");
//        InsertIntoTable.insertData2(6, "Mymensingh");
            ResultSet rs = InsertIntoTable.getDataById(6);
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt(1) + " Name: " + rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
