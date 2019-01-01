import java.util.*;
import java.io.*;
/**
 * Salary class that extends employee class. Holds data for salary employee.
 */
public class Salary extends Employee
{

    private char type = 'S';
    private double yearlySalary;
    /**
     * Default constructor sets instance variables to default values including those in the super class.
     */
    public Salary() {

        super();
        this.yearlySalary = 0;
    }

    /**
     * Constructor that sets variable values of salary employee.
     * @param name name of salary employee.
     * @param emNum employee number of the salary employee.
     * @param department department that employee works in.
     * @param yearlySalary yearly salary of employee.
     */
    public Salary(String name, String empNum, String department, double yearlySalary) {

        super(name, empNum, department);
        this.yearlySalary = yearlySalary;

    }

    /**
     * Setter for the yearly salary of employee.
     * @param yearlySalary the yearly salary of employee to be set.
     */
    public void setYearlySalary(double yearlySalary) {

        this.yearlySalary = yearlySalary;
    }

    /**
     * getter for yearly salary.
     * @return yearly salary of employee.
     */
    public double getYearlySalary() {

        return this.yearlySalary;
    }

    /**
     * Method calculates weekly salary of employee.
     * @return the yearly salary of employee / 52.
     */
    public double calcWeeklySalary() {
        final int NUM_SALARY = 52;
        return this.yearlySalary/NUM_SALARY;

    }

    /**
     * toStirng method that overrides super calss toString and contains additional variables unique to salary employee.
     */
    public String toString() {

        return super.toString() + "Type: " + this.type + "\n" + "Yearly Salary: " + this.yearlySalary;
    }   

    /**
     * Writes data of employee when selected by user in client class.
     * @param PrintWriter pw object that writes data out to file.
     */
    public void writeData(PrintWriter pw) throws IOException {

        pw.print(this.name + "\t" + this.empNum + "\t" + this.department + "\t"+ this.type + "\t" + this.yearlySalary + "\n");
    }
}
