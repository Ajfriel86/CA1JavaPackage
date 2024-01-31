// Author: Angus Friel
// Student Number: sba22066
// GitHub:
// Deployed site:

package CA1JavaPackage;

// Importing libraries
import java.util.*;

public class StudentProcessor{
    // Create an instance of FileService at the class level
    private static FileService fileService = new FileService();

    // Create an instance of StudentService at the class level
    private static StudentService studentService = new StudentService();

    //Defining the main class for running the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // While loop that displays the menu of options for the user
        while (!exit) {
            // Clears the console
            ConsoleClear.clearConsole();

            // Menu items for the user to select from
            System.out.println();
            System.out.println("\u001B[32mMenu:");
            System.out.println("\u001B[34m1. Add new student data in the format stated below: ");
            System.out.println("\u001B[32m-   <first name> <second name>");
            System.out.println("\u001B[32m-   <number of classes>");
            System.out.println("\u001B[32m-   <student number> (format (2)Numbers(3)Letters(4)Numbers, with the last 4 numbers being 2020 or higher)");
            System.out.println();
            System.out.println("\u001B[34m2. Check contents of student.txt is a valid format");
            System.out.println("\u001B[32m-   This will display all the current entries of student.txt ");
            System.out.println();
            System.out.println("\u001B[34m3. Display status.txt");
            System.out.println();
            System.out.println("\u001B[34m4. Display students.txt");
            System.out.println();
            System.out.println("\u001B[31m5. Exit");
            System.out.println();
            System.out.print("\u001B[33mEnter your choice: ");
            System.out.println();
            int choice = scanner.nextInt();
            System.out.println();
            scanner.nextLine();

        }
    }

} 