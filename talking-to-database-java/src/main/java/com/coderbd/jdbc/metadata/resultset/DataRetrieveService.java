package com.coderbd.jdbc.metadata.resultset;


import com.coderbd.jdbc.metadata.preparedStatement.*;
import com.coderbd.jdbc.metadata.conn.MySqlDbConnection;
import com.coderbd.jdbc.metadata.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajail Islam
 */
public class DataRetrieveService {

   static Connection conn = MySqlDbConnection.getConnection();

    public static List<Student> getStudentList() {
        List<Student> list = new ArrayList<>();

        try {
            Student s;
            PreparedStatement stmt = conn.prepareStatement("select *from student");
          
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                s = new Student();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setAge(rs.getInt("age"));

                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataRetrieveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void main(String[] args) {
          List<Student> list = DataRetrieveService.getStudentList();

        for (Student s : list) {
            System.out.println("ID: " + s.getId() + " Name: " + s.getName() + " Age: " + s.getAge());
        }

    }
}

