package com.coderbd.jdbc.metadata.statement;

import com.coderbd.jdbc.metadata.domain.Student;
import java.util.List;

/**
 *
 * @author Rajaul Islam
 */
public class App {

    public static void main(String[] args) {
        List<Student> list = DataRetrieveService.getStudentList();

        for (Student s : list) {
            System.out.println("ID: " + s.getId() + " Name: " + s.getName() + " Age: " + s.getAge());
        }

    }
}
