package com.coderbd.jdbc.metadata.preparedStatement;


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
            while (rs.previous()) {
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

/*
PreparedStatement interface

The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.

Let's see the example of parameterized query:

    String sql="insert into emp values(?,?,?)";  

As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
Why use PreparedStatement?

Improves performance: The performance of the application will be faster if you use PreparedStatement interface because query is compiled only once.
How to get the instance of PreparedStatement?

The prepareStatement() method of Connection interface is used to return the object of PreparedStatement. Syntax:

    public PreparedStatement prepareStatement(String query)throws SQLException{}  

Methods of PreparedStatement interface

The important methods of PreparedStatement interface are given below:
Method	Description
public void setInt(int paramIndex, int value)	sets the integer value to the given parameter index.
public void setString(int paramIndex, String value)	sets the String value to the given parameter index.
public void setFloat(int paramIndex, float value)	sets the float value to the given parameter index.
public void setDouble(int paramIndex, double value)	sets the double value to the given parameter index.
public int executeUpdate()	executes the query. It is used for create, drop, insert, update, delete etc.
public ResultSet executeQuery()	executes the select query. It returns an instance of ResultSet.
Example of PreparedStatement interface that inserts the record

First of all create table as given below:

    create table emp(id number(10),name varchar2(50));  
*/