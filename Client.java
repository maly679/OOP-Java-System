import java.util.Scanner;
import java.util.*;
import java.io.*;
/**
 * Client interface class that reads user choices.
 */
public class Client
{
    private static final int MAX_ATTEMPTS = 3;
    /**
     * main method that processes the choice of user.
     */
    public static void main(String args[]) throws IOException
    {
        Scanner in = new Scanner(System.in);
        Payroll p = new Payroll();
        int attempt_no = 1;
        boolean done = false;
        boolean noErrors = true;
        while (!done) {
            try {
                noErrors = p.loadData(askFile(in));
                done = true;
            } catch (FileNotFoundException fe) {
                System.out.println("File not found in the system.");
                if (attempt_no >= MAX_ATTEMPTS) {
                    System.out.println("Maximum 3 attempts allowed. Exiting.");
                    return;
                } else {
                    System.out.println("Please try again.");
                }
                attempt_no++;
            }
        }
        if (!noErrors) {
            System.out.println("Errors found in the employee data file. Exiting.");
            return;
        }
        showMenu();
        char choice2 = in.next().charAt(0);
        char choice = Character.toUpperCase(choice2);

        while(choice!= 'Q') {

            switch(choice) {
                case 'A': 
                p.addEmployee(enter(in), in);
                showMenu();
                choice2 = in.next().charAt(0);
                choice = Character.toUpperCase(choice2);
                break;
                case 'I': 
                p.printInfo(enter(in));
                showMenu();
                choice2 = in.next().charAt(0);
                choice = Character.toUpperCase(choice2);
                break;
                case 'D': 
                p.deleteEmp(enter(in) , in);
                showMenu();
                choice2 = in.next().charAt(0);
                choice = Character.toUpperCase(choice2);
                break;
                case 'S': 
                p.calculateWeeklySalary(enter(in));
                showMenu();
                choice2 = in.next().charAt(0);
                choice = Character.toUpperCase(choice2);
                break;
                case 'T':
                p.printTopSellers();
                showMenu();
                choice2 = in.next().charAt(0);
                choice = Character.toUpperCase(choice2);
                break;
                case 'P':
                p.printSalaryReport();
                showMenu();
                choice2 = in.next().charAt(0);
                choice = Character.toUpperCase(choice2);
                break;
                case 'W':
                p.writeDataa();
                showMenu();
                choice2 = in.next().charAt(0);
                choice = Character.toUpperCase(choice2);
                break;
                default:
                System.out.print("Invalid character entered. Please try again");
                showMenu();
                choice2 = in.next().charAt(0);
                choice = Character.toUpperCase(choice2);
                break;
            }
        }

        System.out.println ("Thank you for using the Payroll Processing System");
    }

    /**
     * Method that prompts user for employee number and assign it to a variable.
     * @return newEmp2 returns the employee number.
     */
    private static String enter(Scanner in) {
        System.out.print("Enter the employee number: ");
        return in.next();
    }

    /**
     * Method that asks user for name of file.
     * @param  in scanner object that reads information from user.
     * @return filename returns the file name.
     */
    private static String askFile(Scanner in) {
        System.out.print("Enter file name to be loaded: ");
        return in.next();
    }

    /**
     * Menu shown to user.
     */
    private static void showMenu()
    {
        System.out.println("\nMENU:");
        System.out.println("A - Add a new employee");
        System.out.println("I - Print information of an individual employee");
        System.out.println("D - Remove an employee from payroll");
        System.out.println("S - calculate and print the weekly pay of an employee");
        System.out.println("T - This prints the list of Commission employees who are topSellers");
        System.out.println("P - prints salary report");
        System.out.println("W - End of week processing");
        System.out.println();
        System.out.println("Q - Quit the system");
    }

}

