package com.coderbd.jdbc.service;

import com.coderbd.jdbc.domain.Student;

public class Test {

    static StudentService studentService;

    public static void main(String[] args) {
        studentService = new StudentService();
        Student std = new Student(1003, "Ruhul Amin Ok");
        studentService.update(std);
    }
}
