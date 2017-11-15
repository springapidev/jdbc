package com.coderbd.jdbc.metadata.statement;


import com.coderbd.jdbc.metadata.conn.OracleDBConnection;
import com.coderbd.jdbc.metadata.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajail Islam
 */
public class DataRetrieveService {

   static Connection conn = OracleDBConnection.getConnection("xe", "hr","hr");

    public static List<Student> getStudentList() {
        List<Student> list = new ArrayList<>();

        try {
            Student s;
          //  PreparedStatement stmt = conn.prepareStatement("select *from student");
          Statement stmt=conn.createStatement();
           ResultSet rs = stmt.executeQuery("select *from studentbd");
            while (rs.next()) {
                s = new Student();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
            //    s.setAge(rs.getInt("age"));

                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataRetrieveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public static void main(String[] args) {
        for(Student s : getStudentList()){
            System.out.println("ID: "+s.getId()+ " name:  "+s.getName());
        }
    }
}
