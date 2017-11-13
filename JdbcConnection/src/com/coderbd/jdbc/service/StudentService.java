package com.coderbd.jdbc.service;

import com.coderbd.jdbc.connections.OracleDBConnection;
import com.coderbd.jdbc.dao.StudentDao;
import com.coderbd.jdbc.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentService implements StudentDao {

    Connection conn = OracleDBConnection.getConnection("xe", "hr", "hr");

    @Override
    public void save(Student s) {
        try {
            if (s.getStudentName() != null) {
                PreparedStatement stmt = conn.prepareStatement("insert into studentbd(id,student_name ) values(?,?)");
                stmt.setInt(1, s.getId());
                stmt.setString(2, s.getStudentName());

                int i = stmt.executeUpdate();
                System.out.println(i + " record inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Student s) {
        try {
            if (s.getId() != 0) {
                PreparedStatement stmt = conn.prepareStatement("update studentbd set student_name=? where id=?");

                stmt.setString(1, s.getStudentName());
                stmt.setInt(2, s.getId());
                int i = stmt.executeUpdate();
                System.out.println(i + " record Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            if (id != 0) {
                PreparedStatement stmt = conn.prepareStatement("delete from student where id=?");

                stmt.setInt(1, id);

                int i = stmt.executeUpdate();
                System.out.println(i + " records Deleted");
            }
            // conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Student> getStudents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Student getStudent(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
