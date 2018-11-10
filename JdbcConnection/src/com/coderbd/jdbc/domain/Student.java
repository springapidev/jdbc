package com.coderbd.jdbc.domain;
public class Student {
 private int id;
 private String studentName;
 private String email;

    public Student() {
    }

    public Student(int id, String studentName) {
        this.id = id;
        this.studentName = studentName;
    }

    public Student(int id, String studentName, String email) {
        this.id = id;
        this.studentName = studentName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
 
 
}
