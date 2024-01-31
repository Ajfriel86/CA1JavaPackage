// Author: Angus Friel
// Student Number: sba22066
// GitHub:
// Deployed site:

//Declaring package
package CA1JavaPackage;

// Importing libraries
import java.io.*;
import java.util.List;
import java.util.Scanner;

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
        boolean append = file.exists() && file.length() > 0; // Check if file exists and is not empty

        // Declares and initializes 3 different resources in a try block
        try (FileWriter fw = new FileWriter(file, append);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            //  checks if the append variable is true, and if so, it adds a newline character to the output stream,    
            if (append) {
                out.println(); // Add a newline if appending to existing data
            }
            // Write student data to file
            out.println(firstName + " " + secondName);
            out.println(classNumber);
            out.println(studentNumber);
            System.out.println();
            // Displays a message in green to state data was added
            System.out.println("\u001B[32mStudent data added successfully.");
        } 
        // This catches and handles IOException exceptions that might occur during file writing, and displaying an error message in red 
        catch (IOException e) {
            System.out.println();
            System.err.println("\u001B[31m!!!!!! - An error occurred while writing to the file: " + e.getMessage());
        }
    }
    
}
