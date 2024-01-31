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

            / Defining a variable that will be used to return the user to the main menu
            String returnToMenuChoice;

            switch (choice) {
                case 1:
                    // Call the method to add new student data
                    studentService.addNewStudentData(scanner);
                    studentService.processAndWriteData();
                    break;
                case 2:
                        // Check file format using fileService
                        boolean isValidFormat = fileService.isValidStudentFileFormat("students.txt");
                        if (isValidFormat) {
                            // Prints a message in green stating format uis correct
                            System.out.println("\u001B[32mstudents.txt is in the correct format.");
                            System.out.println();
                        } else {
                            //  Displays an error message in red that the format is incorrect
                            System.out.println("\u001B[31mstudents.txt is not in the correct format.");
                            System.out.println();
                            // Display invalid lines
                            fileService.displayInvalidLines("students.txt");
                        }
                        // Ask the user if they want to return to the main menu
                        System.out.println();
                        System.out.print("\\u001B[32mDo you want to return to the main menu? (yes/no): ");
                        System.out.println();
                        //Initializing the variable returnToMenuChoice so the user can return to the main menu
                        returnToMenuChoice = scanner.nextLine();
                        // If the user selects Y or YES the return to the main menu, if the user selects N ot NO then program ends
                        if ("Y".equalsIgnoreCase(returnToMenuChoice) || "YES".equalsIgnoreCase(returnToMenuChoice)) {
                            // Clear the console and return to the main menu
                            ConsoleClear.clearConsole();
                        }
                        break;
                case 3:
                    // Display status.txt using fileService
                    fileService.displayStatusFile();
                    System.out.println();
                    // Ask the user if they want to return to the main menu
                    System.out.print("\\u001B[32mDo you want to return to the main menu? (yes/no): ");
                    //Initializing the variable returnToMenuChoice so the user can return to the main menu
                    returnToMenuChoice = scanner.nextLine();
                    // If the user selects Y or YES the return to the main menu, if the user selects N ot NO then program ends
                    if ("Y".equalsIgnoreCase(returnToMenuChoice) || "YES".equalsIgnoreCase(returnToMenuChoice)) {
                        // Clear the console and return to the main menu
                        ConsoleClear.clearConsole();
                        break;
                    }
                case 4:
                    // Displays the students.txt file
                    fileService.displayStudentsFile();
                    // Ask the user if they want to return to the main menu
                    System.out.println();
                    System.out.print("\\u001B[32mDo you want to return to the main menu? (yes/no): ");
                    //Initializing the variable returnToMenuChoice so the user can return to the main menu
                    returnToMenuChoice = scanner.nextLine();
                    // If the user selects Y or YES the return to the main menu, if the user selects N ot NO then program ends
                    if ("Y".equalsIgnoreCase(returnToMenuChoice) || "YES".equalsIgnoreCase(returnToMenuChoice)) {
                        // Clear the console and return to the main menu
                        ConsoleClear.clearConsole();
                        break;
                    }
                case 5:
                    // end the program 
                    exit = true;
                    break;
                default:
                    // displays an error message if the user selects an invalid choice from the menu 
                    System.out.println("\u001B[31mInvalid choice. Please try again.");
            }
        }

    }
}

