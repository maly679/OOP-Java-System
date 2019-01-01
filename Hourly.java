import java.util.*;
import java.io.*;
/**
 * Hourly class that contains data for hourly empoyee.
 */
public class Hourly extends Employee
{

    private char type = 'H';
    private double hrlyPay;
    private double hrsWorked;
    /**
     * Default constructor sets variables to default values.
     */
    public Hourly() {

        super();
        this.hrlyPay = 0;
        this.hrsWorked = 0;

    }
    /**
     * Constructor that sets variables to values included in parameters.
     * @param name name of hourly employee.
     * @param empNum employee number of hourly employee.
     * @param dept department of hourly employee.
     * @param hrlyPay hourly pay of hourly employee.
     * @param hrsWorked hours worked of hourly employee.
     */
    public Hourly(String name, String empNum, String dept, double hrlyPay, double hrsWorked) {
        super(name, empNum, dept);
        this.hrlyPay = hrlyPay;
        this.hrsWorked = hrsWorked;

    }
    /**
     * setter for hourly pay.
     * @param hrlyPay the hourly pay of employee to be set.
     */
    public void setHrlyPay (double hrlyPay) {

        this.hrlyPay = hrlyPay;

    }
    /**
     * setter for name.
     * @param hrsWorked of employee to be set.
     */
    public void setHrsWorked(double hrsWorked) {

        this.hrsWorked = hrsWorked;
    }
    /**
     * getter for hourly pay.
     * @return hourly pay of employee.
     */
    public double getHrlyPay() {

        return this.hrlyPay;
    }
    /**
     * getter for hours worked.
     * @return hrsWorked the hours worked by employee.
     */
    public double getHrsWorked() {

        return this.hrsWorked;

    }
    /**
     * Calculates the weekly salary of hourly employee.
     * @return weeklySalary return the weekly salary of hourly employee.
     */
    public double calcWeeklySalary() {
        double weeklySalary = 0;
        final int NORMAL_HOURS = 40;
        final double TIME_AND_HALF = 1.5;
        if (this.hrsWorked <= NORMAL_HOURS) {

            weeklySalary = this.hrsWorked * this.hrlyPay;
        } else {
            weeklySalary = ((this.hrsWorked - NORMAL_HOURS) * hrlyPay * TIME_AND_HALF) + (NORMAL_HOURS * hrlyPay);
        }
        return weeklySalary;
    }
    /**
     * toString method that overrides superclass method and print out the values for the variables of hourly employee.
     * @return the variables of the hourly employee, including the superclass ones and the ones unique to the hourly employee.
     */
    public String toString() {

        return super.toString() + "Type: " + this.type + "\n" + "Hourly Pay: " + this.hrlyPay + "\n" + "Hours Worked: " + this.hrsWorked + "\n";

    }
    /**
     * Method that writes data of hourly employee when selected in client class.
     * @param PrintWriter pw object that prints the data of employee to file.
     */
    public void writeData(PrintWriter pw) throws IOException {


        pw.println("\n" + this.name + "\t" + this.empNum + "\t" + this.department + "\t" + this.type + "\t" + this.hrlyPay + "\t" + this.hrsWorked + "\n");
        
    }

}
