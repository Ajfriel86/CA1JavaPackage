// Author: Angus Friel
// Student Number: sba22066
// GitHub:
// Deployed site:

//Declaring package
package CA1JavaPackage;

// Importing necessary Java libraries
import java.io.*;
import java.util.List;
import java.util.Scanner;

// Define a public class named StudentService
public class StudentService {

    // Method to add new student data
    public void addNewStudentData(Scanner scanner) {

        // Variables to store user input and whether the input is valid
        String firstName = "", secondName = "", studentNumber = "";
        int classNumber = 0;
        boolean validInput;

        // Loop for input and validation of the first name
        do {
            System.out.println();
            System.out.print("\u001B[34mEnter first name (letters only): ");
            firstName = scanner.nextLine(); // Read first name
            validInput = firstName.matches("[a-zA-Z]+"); // Check if first name contains only letters
            // if statement that displays an error message in red if the data entered is incorrect
            if (!validInput) {
                System.out.println();
                System.out.println("\u001B[31m!!!!!! - Invalid first name. Must be letters only.");
            }
        } while (!validInput);  // While loops that iterates until the validation is true

        // Loop for input and validation of the second name
        do {
            System.out.println();
            System.out.print("\u001B[34mEnter second name (letters and/or numbers): ");
            secondName = scanner.nextLine(); // Read second name
            validInput = secondName.matches("[a-zA-Z0-9]+"); // Check if second name contains only letters and/or numbers
            // if statement that displays an error message in red if the data entered is incorrect
            if (!validInput) {
                System.out.println();
                System.out.println("\u001B[31m!!!!!! - Invalid second name. Can only contain letters and/or numbers.");
            }
        } while (!validInput); // While loops that iterates until the validation is true

        // Loop for input and validation of the class number
        do {
            System.out.println();
            System.out.print("\u001B[34mEnter class number (1 to 8): ");
            if (scanner.hasNextInt()) {
                classNumber = scanner.nextInt(); // Read class number
                scanner.nextLine(); // Clear buffer after reading integer
                validInput = (classNumber >= 1 && classNumber <= 8); // Validate class number is between 1 and 8
            } else {
                scanner.nextLine(); // Clear buffer if input was not an integer
                validInput = false;
            }
            // if statement that displays an error message in red if the data entered is incorrect
            if (!validInput) {
                System.out.println();
                System.out.println("\u001B[31m!!!!!! - Invalid class number. Must be between 1 and 8.");
            }
        } while (!validInput); // While loops that iterates until the validation is true

        // Loop for input and validation of the student number
        do {
            System.out.println();
            System.out.print("\u001B[34mEnter student number (format 2 numbers, 3 letters, 4 numbers, last 4 being 2020 or higher): ");
            studentNumber = scanner.nextLine(); // Read student number
            validInput = studentNumber.matches("\\d{2}[a-zA-Z]{3}\\d{4}"); // Validate format of student number

            if (validInput) {
                try {
                    // Extract and validate the last 4 digits
                    int last4Digits = Integer.parseInt(studentNumber.substring(studentNumber.length() - 4));
                    validInput = last4Digits >= 2020; // Check if last 4 digits are 2020 or higher
                    // if statement that displays an error message in red if the data entered is incorrect
                    if (!validInput) {
                        System.out.println();
                        System.out.println("\u001B[31m!!!!!! -  Invalid student number. The last 4 digits must be 2020 or higher.");
                    }
                } 
                // This catches a NumberFormatException exception, prints an error message in red to notify the user about the invalid input format
                catch (NumberFormatException e) {
                    System.out.println();
                    System.out.println("\u001B[31m!!!!!! -  Invalid format for student number. Please ensure it follows the specified format.");
                    validInput = false;
                }
            } 
            // prints an error message in red to notify the user about the invalid input format
            else {
                System.out.println();
                System.out.println("\u001B[31m!!!!!! -  Invalid student number. Format: 2 numbers, 3 letters, 4 numbers.");
            }
        } while (!validInput); // While loops that iterates until the validation is true

        // Write the validated student data to a file
        File file = new File("students.txt");
        boolean append = file.exists() && file.length() > 0;
    
        // Try with resources to open and manage these resources for writing to a file.
        try (FileWriter fw = new FileWriter(file, append);   // Open a FileWriter for the specified file
            BufferedWriter bw = new BufferedWriter(fw);      // Wrap FileWriter in a BufferedWriter
            PrintWriter out = new PrintWriter(bw)) {          // Wrap BufferedWriter in a PrintWriter
            
            // Write the student data
            out.print(firstName + " " + secondName); // Avoid println to not add a newline after each entry
            out.print("\n" + classNumber); // Add newline before classNumber
            out.print("\n" + studentNumber); // Add newline before studentNumber, no newline after
    
            System.out.println("\u001B[32mStudent data added successfully.");
        } 
        // Catch for error handling of IOExceptions and displays an error message in red
        catch (IOException e) {
            System.err.println("\u001B[31m!!!!!! - An error occurred while writing to the file: " + e.getMessage());
        }
    }

    // Method to process and write data to a status file
    public void processAndWriteData() {
        // Read student data from a file
        List<Student> students = StudentFileReader.readStudentsFromFile("students.txt");
        try (PrintWriter writer = new PrintWriter("status.txt")) {
            // Write each student's status to the file
            for (Student student : students) {
                writer.println(student.getStudentNumber() + " - " + student.getLastName());
                writer.println(student.determineWorkload());
            }
        } 
        // This catches and handles FileNotFoundException's that might occur during file writing, and displaying an error message in red 
        catch (FileNotFoundException e) {
            System.out.println();
            System.err.println("\u001B[31m!!!!!! - An error occurred: " + e.getMessage());
        }
    }
}
