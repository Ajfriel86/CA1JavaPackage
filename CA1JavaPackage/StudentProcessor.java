// Author: Angus Friel
// Student Number: sba22066
// GitHub:
// Deployed site:

// Declare package
package CA1JavaPackage;

// Importing necessary Java libraries
import java.util.Scanner;

// Main class for processing student data
public class StudentProcessor {

    // Creating instances of FileService and StudentService at the class level
    private static FileService fileService = new FileService();
    private static StudentService studentService = new StudentService();

    // Main method for processing student data with the application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for reading user input
        boolean exit = false; // Boolean flag to help signal different states and control exit of the application
        ConsoleClear.clearConsole(); // Clear the console before application starts

        // Main loop for the menu-driven interface
        while (!exit) {
            displayMenu(); // Displaying the menu options
            int choice = scanner.nextInt(); // Reading the user's choice
            scanner.nextLine(); // Clearing the scanner buffer
            ConsoleClear.clearConsole(); // Clear the console before application starts
            // Switch case to handle different menu choices
            switch (choice) {
                case 1:
                    ConsoleClear.clearConsole(); // Clear the console
                    studentService.addNewStudentData(scanner); // Add new student data
                    studentService.processAndWriteData(); // Process and write data to the file
                    break;
                case 2:
                    ConsoleClear.clearConsole(); // Clear the console
                    fileService.validateAndDisplayFileFormat(); // Validate and display the file format
                    // Check if the user wants to exit the program after validation
                    boolean shouldExit = MenuService.promptReturnToMenu(scanner);
                    if (shouldExit) {
                        exit = true;
                    }
                    break;
                case 3:
                    ConsoleClear.clearConsole(); // Clear the console
                    fileService.handleInvalidEntries(scanner); // Handle invalid entries in the students file
                    // Prompt the user to return to the menu or exit the program
                    exit = MenuService.promptReturnToMenu(scanner);
                    break;
                case 4:
                    ConsoleClear.clearConsole(); // Clear the console
                    fileService.displayStatusFile(); // Display the status file
                    // Prompt the user to return to the menu or exit the program
                    exit = MenuService.promptReturnToMenu(scanner);
                    break;
                case 5:
                    exit = true; // Set the exit flag to true to exit the program
                    break;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please try again."); // Handle invalid menu choices
            }
        }

        scanner.close(); // Close the scanner resource
        System.out.println("\u001B[32mProgram exited. Goodbye :)"); // Exit message
    }

    // Method to display the menu options to the user
    private static void displayMenu() {
        System.out.println();
        System.out.println("\u001B[32mMenu:");
        System.out.println("\u001B[34m1. Add new student data in the format stated below:");
        System.out.println("\u001B[32m-   <first name> <second name>");
        System.out.println("\u001B[32m-   <number of classes>");
        System.out.println("\u001B[32m-   <student number> (format (2)Numbers(3)Letters(4)Numbers, with the last 4 numbers being 2020 or higher)");
        System.out.println();
        System.out.println("\u001B[34m2. Check contents of student.txt is a valid format");
        System.out.println("\u001B[32m-   This will check the format of all the current entries of student.txt and display an error message if incorrect");
        System.out.println();
        System.out.println("\u001B[34m3. Display students.txt");
        System.out.println("\u001B[32m-   This will display all the current entries of student.txt and let you edit any incorrect entries");
        System.out.println();
        System.out.println("\u001B[34m4. Display status.txt");
        System.out.println("\u001B[32m-   This will display all the content of status.txt");
        System.out.println();
        System.out.println("\u001B[31m5. Exit");
        System.out.println("\u001B[33mEnter your choice: ");
    }
}