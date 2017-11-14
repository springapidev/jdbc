package com.coderbd.jdbc.metadata.statement;


import com.coderbd.jdbc.metadata.conn.MySqlDbConnection;
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
    
}
/*
public boolean next():	is used to move the cursor to the one row next from the current position.
 public int getInt(int columnIndex):	is used to return the data of specified column index of the current row as int.
 public int getInt(String columnName):	is used to return the data of specified column name of the current row as int.
 public String getString(int columnIndex):	is used to return the data of specified column index of the current row as String.
 public String getString(String columnName):	is used to return the data of specified column name of the current row as String.
*/