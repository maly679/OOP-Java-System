import java.util.*;
import java.io.*;
/**
 * Super class Employee that acts as base for employees and stores their data.
 */
public class Employee
{

    protected String name;
    protected String empNum;
    protected String department;
    /**
     * Default constructor sets values to empty string.
     */
    public Employee() {

        this.name = "";
        this.empNum = "";
        this.department = "";
    }

    /**
     * Constructor sets variables.
     * @param name is the name of employee.
     * @param empNum is the employee number
     * @param department is the department of the employee.
     */
    public Employee(String name, String empNum, String department) {

        this.name = name;
        this.empNum = empNum;
        this.department = department;
    }

    /**
     * Setter for name.
     * @param n name of employee to be set.
     */
    public void setName(String n) {

        this.name = n;
    }

    /**
     * Setter for employee number.
     * @param empnum of employee to be set.
     */
    public void setEmpNum(String empNum) {

        this.empNum = empNum;

    }

    /**
     * Setter for department.
     * @param dept name of department to be set.
     */
    public void setDepartment(String dept) {

        this.department = dept;
    }

    /**
     * getter for name.
     * @return name of employee.
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter for employee number.
     * @return employee number of employee.
     */
    public String getEmpNum() {

        return this.empNum;
    }

    /**
     * getter for name.
     * @return department of employee.
     */
    public String getDept() {

        return this.department;
    }

    /**Tests whether two employee objects have the same employee number.
     * @param Employee other used as object to compare to.
     */
    public boolean equals(Employee other) {

        boolean result = false;
        if (this.empNum.equals(other.empNum)) {
            result = true;
            

        }
        return result;
    }

    /**
     * toString that overrides java toString method that prints object data.
     * @return string of employee object instance variables.
     */
    public String toString() {
        return "Name: " + this.name + "\n" + "Employee Number: " + this.empNum + "\n" + "Department: " + this.department + "\n";
    }

    /**
     * Method used as base for subclass methods to override in order to execute it in processor class.
     * @return 0.
     */
    public double calcWeeklySalary() {
        return 0;
    }

}
