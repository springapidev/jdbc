package com.coderbd.jdbc.query;

import com.coderbd.jdbc.domain.Employees;
import java.util.List;

public class App {
    public static void main(String[] args) {
       List<Employees> employeeList = DataRetrieveService.getEmployees();
       for(Employees e : employeeList){
           System.out.println("Emp ID: "+e.getEmployeeID()+" Name: "+e.getLastName()); 
      
       }
    }
  
}
