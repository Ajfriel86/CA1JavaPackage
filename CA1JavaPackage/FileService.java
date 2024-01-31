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
    
}
