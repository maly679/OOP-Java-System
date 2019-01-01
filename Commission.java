import java.io.*;
import java.util.*;
/**
 * Class that holds data for commission employee.
 */
public class Commission extends Employee
{

    private char type = 'C';
    private int numWeeks;
    private double baseWeeklySalary;
    private double weeklySales;
    private double yearlySales;
    private double commissionRate; 
    /**
     * default constructor that initializes variables with default values.
     */
    public Commission() {

        super();
        this.numWeeks = 0;
        this.baseWeeklySalary = 0;
        this.weeklySales = 0;
        this.yearlySales = 0;
        this.commissionRate = 0;
    }
    /**
     * construuctor that initializes variables with values included in parameters, including those in the super class.
     * @param name name of commission employee.
     * @param empNum employee number of salary employee.
     * @param department department of commission employee.
     * @param numWeeks number of weeks since employment
     * @param baseSalary the base salary of commission employee.
     * @param weeklySales the weekly sales of the employee.
     * @param yearlySales the yearly sales of the employee.
     * @param commission the commission rate of the employee.
     */
    public Commission (String name, String empNum, String department, int numWeeks, double baseSalary, double weeklySales, double yearSales, double commission) {

        super(name, empNum, department);
        this.numWeeks = numWeeks;
        this.baseWeeklySalary = baseSalary;
        this.weeklySales = weeklySales;
        this.yearlySales = yearSales;
        this.commissionRate = commission;
    }
    /**
     * setter for number of weeks.
     * @param numWeeks the number of weeks to be set.
     */
    public void setNumWeeks(int numWeeks) {

        this.numWeeks = numWeeks;
    }
    /**
     * setter for the base weekly salary.
     * @param baseSalary base salary of employee.
     */
    public void setBaseWeeklySalary(double baseSalary) {
        this.baseWeeklySalary = baseSalary;
    }
    /**
     * setter for weekly sales.
     * @param weeklySales the weekly sales of employee to be set.
     */
    public void setWeeklySales(int weeklySales) {
        this.weeklySales = weeklySales;
    }
    /**
     * setter for commission rate.
     * @param the commission rate of employee to be set.
     */
    public void setCommissionRate (double commission) {
        this.commissionRate = commission;
    }
    /**
     * getter for number of weeks.
     * @return number of weeks of employee.
     */
    public int getNumWeeks() {
        return this.numWeeks;
    }
    /**
     * getter for base weekly salary.
     * @return base weekly salary of employee.
     */
    public double getBaseWeeklySalary() {

        return this.baseWeeklySalary;
    }
    /**
     * getter for weekly sales.
     * @return weekly sales of employee.
     */
    public double getWeeklySales() {
        return this.weeklySales;

    }
    /**
     * getter for yearly sales.
     * @return yearly sales of employee.
     */
    public double getYearlySales() {
        return this.yearlySales;
    }
    /**
     * getter for commission rate.
     * @return commission rate of employee.
     */
    public double getCommissionRate() {

        return this.commissionRate;
    }
    /**
     * Method calculates the weekly salary for a commission employee.
     * @return the calculated yearly salary of employee.
     */
    public double calcWeeklySalary() {

        return (this.weeklySales * this.commissionRate) + this.baseWeeklySalary;

    }
    /**
     * Determines whether a commission employee is a top seller.
     * @return top returns whether the employee is a top seller or not.
     */
    public boolean topSeller() {
        final int MIN_AMOUNT = 1500;
        boolean top;
        top =  (this.yearlySales / this.numWeeks) > MIN_AMOUNT && this.weeklySales > MIN_AMOUNT;
        return top;
    }
    /**
     * toString method that overrides super class toString and extends its output with variables that are unique to commission employees.
     * @return the variable data.
     */ 
    public String toString() {

        return super.toString() + "Type: " + this.type + "\n" + "Number of Weeks since Beginning of year: " + this.numWeeks + "\n" 
        + "Base Weekly Salary: " + this.baseWeeklySalary + "\n" + "Weekly Sales: " + this.weeklySales + "\n" + "Yearly Sales: " + this.yearlySales + "\n"
        + "Commission Rate: " + this.commissionRate + "\n" + "TopSellers: " + this.topSeller();
    }
    /**
     * Wrtes commission employee data to file when selected by client.
     * @param PrintWriter pw object that writes data to file.
     */
    public void writeData(PrintWriter pw)  throws IOException{    
        
        pw.println("\n" + this.name + "\t" + this.empNum + "\t" + this.department + "\t" + this.type + "\t" + this.numWeeks + "\t" + this.baseWeeklySalary + "\t" + this.weeklySales
            + "\t" + this.yearlySales + "\t" + this.commissionRate);

    }

}
