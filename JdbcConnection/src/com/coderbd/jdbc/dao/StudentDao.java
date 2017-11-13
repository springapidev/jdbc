package com.coderbd.jdbc.dao;

import com.coderbd.jdbc.domain.Student;
import java.util.List;

public interface StudentDao {
//we shall save student object into database.

    public void save(Student s);
//we shall update student object according to student ID into database.

    public void update(Student s);
//we shall delete student object from database according to ID.

    public void delete(int id);
//if we want to get all rows from database table then we shall return list of Student

    public List<Student> getStudents();

    //if we want to get a single / one row from database table according to ID then we shall return  Student 
    public Student getStudent(int id);
}
