package com.coderbd.conn;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rajail Islam
 */
public class AccessConnection {

    public static void main(String[] args) {
     
            getAccessConnection();
       
    }


    public static Connection getAccessConnection() {
        String user = "";
        String pass = "";
        Connection con = null;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://E:\\j2ee\\accessdb/firoza1.accdb", user, pass);
            System.out.println(":::Connected:::");
         }catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
