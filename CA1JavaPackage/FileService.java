// Author: Angus Friel
// Student Number: sba22066
// GitHub:
// Deployed site:

//Declaring package
package CA1JavaPackage;

// Importing libraries
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            // Print an error message if an IOException occurs
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
                    System.out.println("\u001B[31m!!!!!! - Invalid data found in file at entry number " + lineCount + ": " + studentNumberLine);
                    return false;
                }
            }
        } catch (IOException e) {
            // Print an error message if an IOException occurs
            System.err.println("\u001B[31mAn error occurred while reading the file: " + e.getMessage());
            return false; // Return false if the file format is invalid
        }
        return true; // Return true if the file format is valid
    }

    
}
