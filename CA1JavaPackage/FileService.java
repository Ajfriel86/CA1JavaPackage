// Author: Angus Friel
// Student Number: sba22066
// GitHub:
// Deployed site:

//Declaring package
package CA1JavaPackage;

// Importing necessary Java libraries
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class FileService handles file operations related to student data
public class FileService {

    // Method to read lines from a file and return them as a List of Strings
    public List<String> readLinesFromFile(String fileName) {
        List<String> lines = new ArrayList<>(); // Create a new ArrayList to hold the lines
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line); // Add each line read from the file to the list
            }
        } catch (IOException e) {
            // Print an error message in red if an IOException occurs
            System.err.println("\u001B[31mAn error occurred while reading the file: " + e.getMessage());
        }
        return lines; // Return the list of lines
    }

    // Method to validate the format of a student file
    public boolean isValidStudentFileFormat(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String nameLine;
            int lineCount = 0; // Counter for the number of lines read
            while ((nameLine = reader.readLine()) != null) {
                lineCount++;
                nameLine = nameLine.trim();
                if (nameLine.isEmpty()) {
                    continue; // Skip empty lines
                }

                String[] parts = nameLine.split("\\s+", 2); // Split name line into parts
                String classNumberLine = reader.readLine(); // Read next line for class number
                String studentNumberLine = reader.readLine(); // Read next line for student number

                // Validate the format of the data in the file
                if (!(parts.length == 2 && parts[0].matches("[a-zA-Z]+") && parts[1].matches("[a-zA-Z0-9]+")
                        && classNumberLine.matches("\\d+") && classNumberLine.length() <= 1
                        && studentNumberLine.matches("\\d{2}[a-zA-Z]{2,}[0-9]+") && studentNumberLine.length() >= 6)) {
                    System.out.println("\u001B[31m!!!!!! - Invalid data found in file at entry number " + lineCount + ": " + nameLine);
                    return false; // Return false if the data format is invalid
                }

                // Additional check for specific student number criteria - last 4 digits being 2020 or higher
                String last4DigitsStr = studentNumberLine.substring(studentNumberLine.length() - 4);
                int last4Digits = Integer.parseInt(last4DigitsStr);
                if (last4Digits < 2020) {
                    // Prints an error message in red if invalid data was found in file
                    System.out.println("\u001B[31m!!!!!! - Invalid data found in file at entry number " + lineCount + ": " + studentNumberLine);
                    return false;
                }
            }
        } catch (IOException e) {
            // Print an error message in red if an IOException occurs
            System.err.println("\u001B[31mAn error occurred while reading the file: " + e.getMessage());
            return false; // Return false if the file format is invalid
        }
        return true; // Return true if the file format is valid
    }

    // Method to display the contents of the status file
    public void displayStatusFile() {
        File file = new File("status.txt");
        if (!file.exists()) {
            // Displays an error message in red if the file does not exist
            System.out.println("\u001B[31mstatus.txt does not exist.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print each line of the file
            }
        } catch (IOException e) {
            // Print an error message in red if an IOException occurs
            System.err.println("\u001B[31m!!!!!! - An error occurred while reading the file: " + e.getMessage());
        }
    }
    
    // Method to display invalid lines from a file
    public void displayInvalidLines(String fileName) {
        List<String> invalidLines = readLinesFromFile(fileName); // Read lines from the file
        if (!invalidLines.isEmpty()) {
            System.out.println("\u001B[31m!!!!!! - Invalid data found in file:");
            for (String line : invalidLines) {
                System.out.println(line); // Print each invalid line
            }
        }
    }

    // Method to display the contents of the students file
    public void displayStudentsFile() {
        List<String> studentLines = readLinesFromFile("students.txt"); // Read lines from the students file
        if (!studentLines.isEmpty()) {
            System.out.println("\u001B[32mContents of students.txt:");
            for (String line : studentLines) {
                System.out.println(line); // Print each line from the students file
            }
        }
    }

    // Method to find and return a list of line-numbers where invalid entries are found
    public List<Integer> findInvalidEntries(String fileName) {
        // An array list that represent the line numbers in a file where invalid data entries are found.
        List<Integer> invalidEntries = new ArrayList<>();
        // Keeps track of the number of lines that have been read from a file
        int lineCount = 0;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String nameLine, classNumberLine, studentNumberLine;
            
            while ((nameLine = reader.readLine()) != null) {
                lineCount++; // Increment line count for each line read (Name line)
                nameLine = nameLine.trim(); // Remove leading and trailing whitespace
        
                classNumberLine = reader.readLine();
                if (classNumberLine == null) break; // Exit if file ends unexpectedly
                lineCount++; // Increment line count (Class number line)
                classNumberLine = classNumberLine.trim(); // Remove leading and trailing whitespace
        
                studentNumberLine = reader.readLine();
                if (studentNumberLine == null) break; // Exit if file ends unexpectedly
                lineCount++; // Increment line count (Student number line)
                studentNumberLine = studentNumberLine.trim(); // Remove leading and trailing whitespace
        
                // Check the validity of the student entry
                if (!isValidStudentEntry(nameLine, classNumberLine, studentNumberLine)) {
                    invalidEntries.add(lineCount - 2); // Add start line number of invalid entry
                }
            }
        } catch (IOException e) {
            // Handle IO exception, print an error message in red
            System.err.println("\u001B[31mAn error occurred while reading the file: " + e.getMessage());
        }
        // returns the invalid/incorrect formatted entries from the file
        return invalidEntries;
        
    }
    
    // Method to correct invalid entries 
    public void correctEntry(String fileName, int startLine, Scanner scanner) {
        // Read the lines from the specified file into a list
        List<String> lines = readLinesFromFile(fileName);
        
        // Prompt the user for corrected information
        System.out.println("\u001B[32mCorrecting entry at line " + startLine + ".");
        System.out.print("\u001B[32mEnter corrected first name and last name: ");
        String name = scanner.nextLine();
        System.out.print("\u001B[32mEnter corrected class number: ");
        String classNumber = scanner.nextLine();
        System.out.print("\u001B[32mEnter corrected student number: ");
        String studentNumber = scanner.nextLine();
    
        // Replace the lines in the list with the corrected data
        lines.set(startLine - 1, name);            // Update the name line
        lines.set(startLine, classNumber);         // Update the class number line
        lines.set(startLine + 1, studentNumber);   // Update the student number line
    
        // Rewrite the file with the corrected data
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Write each line from the updated list back to the file
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            // Handle IO exception, print an error message
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
    
    
    private boolean isValidStudentEntry(String nameLine, String classNumberLine, String studentNumberLine) {
        // Validating the name
        String[] parts = nameLine.split("\\s+", 2);
        if (parts.length != 2 || !parts[0].matches("[a-zA-Z]+") || !parts[1].matches("[a-zA-Z]+")) {
            return false; // Return false if the name format is invalid
        }
    
        // Validating the class number
        if (!classNumberLine.matches("\\d+")) {
            return false; // Return false if the class number format is invalid
        }
    
        // Validating the student number
        if (!studentNumberLine.matches("\\d{2}[a-zA-Z]{3}\\d{4}")) {
            return false; // Return false if the student number format is invalid
        }
    
        // Check the last four digits of the student number are higher than 2020
        try {
            int lastFourDigits = Integer.parseInt(studentNumberLine.substring(studentNumberLine.length() - 4));
            if (lastFourDigits <= 2020) {
                return false; // Returns false if the last four digits are not valid
            }
        } catch (NumberFormatException e) {
            return false; // Returns false if there is an exception
        }
    
        return true; // Returns true if all validation passes
    }

    public void validateAndDisplayFileFormat() {
        // Check if "students.txt" is in the correct format
        boolean isValidFormat = isValidStudentFileFormat("students.txt");
        
        if (isValidFormat) {
            // If the file is in the correct format, display a success message in green
            System.out.println("\u001B[32mstudents.txt is in the correct format.");
        } else {
            // If the file is not in the correct format, notify the user in red
            System.out.println("\u001B[31m!!!!! - students.txt is not in the correct format.");
            
            // Display lines in "students.txt" that do not conform to the expected format
            displayInvalidLines("students.txt");
        }
    }    

    // Method to handle invalid entries
    public void handleInvalidEntries(Scanner scanner) {
        // Find invalid entries in the "students.txt" file
        List<Integer> invalidEntries = findInvalidEntries("students.txt");

        if (invalidEntries.isEmpty()) {
            // If no invalid entries are found, display the content of "students.txt"
            displayStudentsFile();
        } else {
                // If invalid entries are found, notify the user
                System.out.println("\u001B[31m!!!!! - Invalid entries found in students.txt.");
                
                // Read the lines from "students.txt" and add them to an array list
                List<String> lines = readLinesFromFile("students.txt");

                // Loop through each invalid entry
                for (int invalidLine : invalidEntries) {
                    // Display information about the invalid entry
                    System.out.println("Invalid entry at line " + invalidLine + ": " + lines.get(invalidLine - 1));
                    System.out.print("Do you want to correct this entry? (Y/N): ");
                    String userChoice = scanner.nextLine();
                    
                    // If statement for the user if they choose to correct the entry
                    if ("Y".equalsIgnoreCase(userChoice)) {
                        correctEntry("students.txt", invalidLine, scanner); // Call the correction method
                        System.out.println("\u001B[32mEntry corrected."); // Display message in green for the corrected entry 
                        lines = readLinesFromFile("students.txt"); // Refresh the lines after correction
                    }
                }
            }
    }
}
