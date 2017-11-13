package com.coderbd.jdbc.domain;
public class Employees {
  private int employeeID;
  private String lastName;

    public Employees() {
    }

    public Employees(int employeeID, String lastName) {
        this.employeeID = employeeID;
        this.lastName = lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
  
  
}
