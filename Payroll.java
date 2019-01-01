import java.util.*;
import java.io.*;
import java.util.ArrayList;
/**
 * Payroll class acts as processor for employee data.
 */
public class Payroll
{
    private ArrayList<Employee> employees;
    static private final double SALARY_MAX = 480000;
    static private final double SALARY_MIN = 24000;
    static private final double MIN_WAGE = 9.95;
    static private final double HOURS_MAX = 70;
    static private final double BASEPAY_MIN = 400;
    static private final double COMMISSION_MAX = 20;

    /**
     * Constructor that initializes employees array list.
     */
    public Payroll() {
        employees = new ArrayList<Employee>();

    }

    /**
     * Loads the data in file to the array list of employee objects.
     * @param filename file name that is used to load the data.
     * @return true if no error found, false if errors found
     */
    public boolean loadData(String filename) throws FileNotFoundException {
        Scanner filein = new Scanner(new File(filename));
        boolean noErrors = true;
        int lineno = 0;
        while (filein.hasNext()) {
            lineno++;
            String name = filein.next();
            String empNum = filein.next();
            String department = filein.next();
            char type1 = filein.next().charAt(0);
            char type = Character.toUpperCase(type1);
            if (type == 'H') {
                if (!processH(filein, name, empNum, department, lineno)) {
                    noErrors = false;
                }
            } else {
                if (type == 'S') {
                    if (!processS(filein, name, empNum, department, lineno)) {
                        noErrors = false;
                    }
                } else {
                    if (type == 'C') {
                        if (!processC(filein, name, empNum, department, lineno)) {
                            noErrors = false;
                        }

                    } else {
                        System.out.println("Line " + lineno + ": Wrong employee type " + type);
                        filein.nextLine();
                        noErrors = false;
                    }
                }
            }
        }
        filein.close();
        return noErrors;
    }

    /**
     * Processes the creation of an hourly employee object.
     * @param filein the scanner object used to read data from file.
     * @param name name of employee read from loadData method.
     * @param empNum employee number read from loadData method.
     * @param department name of department read from laodData method.
     * @oaram lineno to denote the line number where error occurs
     * @return true if no errors found else, return false
     */
    private boolean processH(Scanner filein, String name, String empNum, String department, int lineno){
        try {
            double hrlyPay = filein.nextDouble();
            double hrsWorked = filein.nextDouble();
            if (isValidHourlyEmployeeDetails(hrlyPay, hrsWorked)) {
                Employee h = new Hourly(name, empNum, department, hrlyPay, hrsWorked);
                employees.add(h);
                System.out.println("Line " + lineno + ": Valid transaction. Employee " + name + " successfully added");
                return true;
            } else {
                System.out.println("Line " + lineno + ": Invalid transaction. Employee " + name + " not added");
                return false;
            }
        } catch (InputMismatchException ie) {
            System.out.println("Error: Entered string but a numeric value was expected.");
            System.out.println("Line " + lineno + ": Invalid transaction. Employee " + name + " not added");
            filein.nextLine();
            return false;
        }
    }

    /**
     * Processes the creation of a salary employee object.
     * @param filein the scanner object used to read data from file.
     * @param name name of employee read from loadData method.
     * @param empNum employee number read from loadData method.
     * @param department name of department read from laodData method.
     * @param lineno line number of the file
     * @return true if no errors found, else false
     */
    public boolean processS(Scanner filein, String name, String empNum, String department, int lineno) {
        try {
            double yearly = filein.nextDouble();
            if (isValidSalariedEmployeeDetails(yearly)) {
                Employee s = new Salary(name, empNum, department, yearly);
                employees.add(s);
                System.out.println("Line " + lineno + ": Valid transaction. Employee " + name + " successfully added");
                return true;
            } else {
                System.out.println("Line " + lineno + ": Invalid transaction. Employee " + name + " not added");
                return false;
            }
        } catch (InputMismatchException ie) {
            System.out.println("Error: Entered string but a numeric value was expected.");
            System.out.println("Line " + lineno + ": Invalid transaction. Employee " + name + " not added");
            filein.nextLine();
            return false;
        }
    }

    /**
     * Processes the creation of a commission    employee object.
     * @param filein the scanner object used to read data from file.
     * @param name name of employee read from loadData method.
     * @param empNum employee number read from loadData method.
     * @param department name of department read from laodData method.
     * @param lineno line number of the file being processed
     * @return true if no errors found else return false
     */
    private boolean processC(Scanner filein, String name, String empNum, String department, int lineno) {
        try {
            int weeks = filein.nextInt();
            double baseWeekly = filein.nextDouble();
            double weeklySales = filein.nextDouble();
            double yearlySales = filein.nextDouble();
            double commission = filein.nextDouble();
            if (isValidCommissionedEmployeeDetails(weeks, baseWeekly, weeklySales, yearlySales, commission)) {
                Employee c = new Commission(name, empNum, department, weeks, baseWeekly, weeklySales, yearlySales, commission);
                employees.add(c);
                System.out.println("Line " + lineno + ": Valid transaction. Employee " + name + " successfully added");
                return true;
            } else {
                System.out.println("Line " + lineno + ": Invalid transaction. Employee " + name + " not added");
                return false;
            }
        } catch (InputMismatchException ie) {
            System.out.println("Error: Entered string but a numeric value was expected.");
            System.out.println("Line " + lineno + ": Invalid transaction. Employee " + name + " not added");
            filein.nextLine();
            return false;
        }
    }

    /**
     * Method that prints the information of an employee, which uses the toString values.
     * @param newEmp2 contains the employee number
     * 
     */
    public void printInfo(String newEmp2) {
        boolean there = false;
        for (int i = 0; i < employees.size(); i++) {
            if (newEmp2.equals(employees.get(i).getEmpNum())) {

                System.out.println(employees.get(i));
                there = true;

            }
        }
        if(!there) {

            System.out.println("Invalid employee number.");
        }

    }

    /**
     * Method that checks if an employee number is within the array list of employee objects.
     * @param empNum the employee number.
     * @return index returns -1 if the employee index is not present, otherwise returns index of employee belonging to employee number.
     */
    private int checkNum(String empNum) {
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            if(empNum.equals(employees.get(i).getEmpNum())) {
                index = i;

            }
        }
        return index;
    }

    /**
     * Method that deletes an employee from array list of employee objects.
     * @param in scanner object that allows us to read information from user.
     */
    public void deleteEmp(String empNum, Scanner in) {
        int i = checkNum(empNum);
        if (i != -1) {

            System.out.print(employees.get(i).getName() + "\t" + employees.get(i).getEmpNum() + "\nAre you sure you would like to delete this employee? ");
            char answer = in.next().charAt(0);

            if(answer == 'Y') {
                employees.remove(i);
                System.out.println("Employee has succesfully been removed.");
            } else {
                System.out.println("Employee removal unsucessfull.");
            }

        } else {
            System.out.println("Invalid employee number entered.");
        }
    }

    /**
     * Method that adds employee to array list.
     * @param in scanner object that reads information from user.
     */
    public void addEmployee(String newEmp, Scanner in) {

        int i = checkNum(newEmp);
        if (i != -1) {
            System.out.println("Employee with number " + newEmp + " already exists.");
        } else {

            System.out.print("Enter the type of employee: ");
            char type1 = in.next().charAt(0);
            char type = Character.toUpperCase(type1);
            if (type == 'H') {
                readH2(in, newEmp);

            } else {
                if (type == 'S') {

                    readS2(in, newEmp);
                } else {
                    if (type == 'C') {

                        readC2(in, newEmp);
                    } else {
                        System.out.println("Invalid character entered.");
                    }
                }
            }
        }
    }

    /**
     * Method that calculates the weekly salary of employee.
     * @param empNum that has employee id
     */
    public void calculateWeeklySalary(String empNum) {
        int i = checkNum(empNum);
        if (i != -1) {
            System.out.println("Weekly Salary: " + employees.get(i).calcWeeklySalary());
        } else {
            System.out.println("Invalid employee number enetered.");
        }
    }

    /**
     * Method that prints the salary report of employees.
     * 
     */
    public void printSalaryReport() {
        for(int i = 0; i < employees.size(); i++) {

            if(employees.get(i) instanceof Hourly) {

                System.out.printf("%-25s%-27s%-20s$%-25.2f%n", employees.get(i).getName(), employees.get(i).getEmpNum(),"Hourly", employees.get(i).calcWeeklySalary());

            } else {
                if (employees.get(i) instanceof Salary) {

                    System.out.printf("%-25s%-27s%-20s$%-25.2f%n", employees.get(i).getName(), employees.get(i).getEmpNum(),"Salary", employees.get(i).calcWeeklySalary());
                } else {
                    if (employees.get(i) instanceof Commission) {

                        System.out.printf("%-25s%-27s%-20s$%-25.2f%nn", employees.get(i).getName(), employees.get(i).getEmpNum(),"Commission", employees.get(i).calcWeeklySalary());
                    }
                }

            }
        }
    }

    /**
     * Method that writes data to file.
     */
    public void writeDataa() throws IOException{
        PrintWriter pw = new PrintWriter(new File("in2.txt"));
        for(int i = 0; i < employees.size(); i++) {
            if(employees.get(i) instanceof Hourly) {
                Hourly h = (Hourly) employees.get(i);
                h.setHrsWorked(0);
                h.writeData(pw);
            } else {

                if (employees.get(i) instanceof Commission) {
                    Commission c = (Commission) employees.get(i);
                    c.setWeeklySales(0);
                    c.setNumWeeks(c.getNumWeeks() + 1);
                    c.writeData(pw);
                } else {
                    if (employees.get(i) instanceof Salary) {
                        Salary s = (Salary) employees.get(i);
                        s.writeData(pw);
                    }
                }
            }

        }
        pw.close();

        System.out.println("Data successfully writtent to file");
    }

    /**
     * Method that prints the top selling commission employees.
     */
    public void printTopSellers() {

        for (int i = 0; i < employees.size(); i++) {

            if(employees.get(i) instanceof Commission) {
                Commission newC = (Commission) employees.get(i);

                if (newC.topSeller()) {
                    System.out.println("Top Seller: " + newC.getName() + "\t" + newC.getEmpNum());
                }

            }

        }
    }

    /**
     * Method that reads employee name.
     * @return name2 returns the name of employee.
     */
    private String readsEmp() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the name of new employee: ");
        return in.nextLine();
    }

    /**
     * Method that reads the deparment of employee.
     * @return dept returns the department of employee.
     */
    private String readsDept() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the department: ");
        return in.nextLine();
    }

    /**
     * Reads the hourly employee information.
     * @param in scanner object that reads information from user.
     * @param newEmp employee number of hourly employee.
     */
    private void readH2(Scanner in, String newEmp) {
        try {
            String name2 = readsEmp();
            String dept = readsDept();
            System.out.print("Enter hourly pay rate: ");
            double hourlyPay = in.nextDouble();
            System.out.print("Enter hours worked this week: ");
            double hrsWorkedWeek = in.nextDouble();
            if (isValidHourlyEmployeeDetails(hourlyPay, hrsWorkedWeek)) {
                Employee h2 = new Hourly(name2, newEmp, dept, hourlyPay, hrsWorkedWeek);
                employees.add(h2);
                System.out.println("Employee successfully added.");
            } else {
                System.out.println("Employee not added");
            }
        } catch (InputMismatchException ie) {
            System.out.println("Error: Entered string but a numeric value was expected.");
        }
        in.nextLine();
    }

    /**
     * Method that validates salaried employee data
     * @param salary yearly salary
     * @return true if salary details are correct
     */
    private boolean isValidSalariedEmployeeDetails(double salary) {
        if (salary >= SALARY_MIN && salary <= SALARY_MAX) {
            return true;
        } else {
            System.out.println("Error: Salary should be in the range $" + SALARY_MIN + ", $" + SALARY_MAX);
            return false;
        }
    }

    /**
     * Method that validates hourly employee data
     * @param hourlyPay hourly pay
     * @param hrsWorkedWeek hours worked this week
     * @return true if hourly employee details are valid, else return false
     */
    private boolean isValidHourlyEmployeeDetails(double hourlyPay, double hrsWorkedWeek) {
        boolean isValid = true;
        if (hourlyPay < 0.0) {
            System.out.println("Error: Hourly pay cannot be negative");
            isValid = false;
        }
        if (hourlyPay < MIN_WAGE) {
            System.out.println("Error: Hourly pay should be minimum " + MIN_WAGE);
            isValid = false;
        }
        if (hrsWorkedWeek < 0.0) {
            System.out.println("Error: Hours worked this week can not be negative");
            isValid = false;
        }
        if (hrsWorkedWeek > HOURS_MAX) {
            System.out.println("Error: Hours worked this week can not be more than " + HOURS_MAX);
            isValid = false;
        }
        return isValid;
    }

    /**
     * Reads the salary employee information.
     * @param in scanner object that reads information from user.
     * @param newEmp employee number of salary employee.
     */
    private void readS2(Scanner in, String newEmp) {
        try {
            String name2 = readsEmp();
            String dept = readsDept();
            System.out.print("Enter the yearly salary: ");
            double yearly = in.nextDouble();
            if (isValidSalariedEmployeeDetails(yearly)) {
                Employee s2 = new Salary(name2, newEmp, dept, yearly);
                employees.add(s2);
                System.out.println("Employee successfully added.");
            } else {
                System.out.println("Employee not added");
            }
        } catch (InputMismatchException ie) {
            System.out.println("Error: Entered string but a numeric value was expected.");
        }
        in.nextLine();
    }

    /**
     * Reads the commission employee information.
     * @param in scanner object that reads information from user.
     * @param newEmp employee number of commission employee.
     */
    private void readC2(Scanner in, String newEmp) {
        try {
            String name2 = readsEmp();
            String dept = readsDept();
            System.out.print("Enter the number of weeks since start of year or employment: ");
            int numWeeksSinceStart = in.nextInt();
            System.out.print("Enter the base weekly salary: ");
            double baseWeekly = in.nextDouble();
            System.out.print("Enter this week's sales: ");
            double weeklySales = in.nextDouble();
            System.out.print("Enter sales this year, discluding this week: ");
            double yearlySales = in.nextDouble();
            System.out.print("Enter the commission rate: ");
            double commissionRate = in.nextDouble();
            if (isValidCommissionedEmployeeDetails(numWeeksSinceStart, baseWeekly, weeklySales, yearlySales, commissionRate)) {
                Employee c2 = new Commission(name2, newEmp, dept, numWeeksSinceStart, baseWeekly, weeklySales, yearlySales, commissionRate);
                employees.add(c2);
                System.out.println("Employee successfully added.");
            } else {
                System.out.println("Employee not added");
            }
        } catch (InputMismatchException ie) {
            System.out.println("Error: Entered string but a numeric value was expected.");
        }
        in.nextLine();
    }

    /**
     * Method that verifies commissioned employee data
     * @param numWeeks number of weeks since start
     * @param baseSalary base salary
     * @param weeklySales sales for this week
     * @param yearlysales yearly sales excluding this week
     * @param commission commission rate
     * @return true if commission employee details are valid, else returns false
     */
    private boolean isValidCommissionedEmployeeDetails(int numWeeks, double baseSalary,
                                                       double weeklySales, double yearlysales, double commission) {
        boolean isValid = true;
        if (numWeeks < 0) {
            System.out.println("Error: Number of weeks since start can not be negative");
            isValid = false;
        }
        if (baseSalary < BASEPAY_MIN) {
            System.out.println("Error: Base weekly salary can not be less than $"+ BASEPAY_MIN);
            isValid = false;
        }
        if (weeklySales < 0.0) {
            System.out.println("Error: Sales for this week can not be negative");
            isValid = false;
        }
        if (yearlysales < 0.0) {
            System.out.println("Error: Yearly Sales can not be negative");
            isValid = false;
        }
        if (commission < 0.0) {
            System.out.println("Error: Commission rate can not be negative");
            isValid = false;
        }
        if (commission > COMMISSION_MAX) {
            System.out.println("Error: Commission rate can not be more than " + COMMISSION_MAX);
            isValid = false;
        }
        return isValid;

    }

}
