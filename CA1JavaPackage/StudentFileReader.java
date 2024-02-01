// Author: Angus Friel
// Student Number: sba22066
// GitHub:
// Deployed site:

//Declaring package
package CA1JavaPackage;

// Importing necessary Java libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Define a public class named StudentFileReader
public class StudentFileReader {

// Method to read student data from a file and return a list of students
public static List<Student> readStudentsFromFile(String fileName) {
    // Creating an array list of students that can hold elements from Student
    List<Student> students = new ArrayList<>();

    // Try block with resources, used to open a file for reading and initializing of a BufferedReader to read the file's contents
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String nameLine;
        // This is a while loop that reads lines from the file using the readLine() method of the BufferedReader 
        while ((nameLine = reader.readLine()) != null) {
            nameLine = nameLine.trim(); // Trim leading and trailing whitespace
            if (nameLine.isEmpty()) {
                continue; // Skip empty lines
            }

            String[] parts = nameLine.split("\\s+", 2); // Splitting by space for first and second names
            String classNumberLine = reader.readLine(); // Next line for class number
            String studentNumberLine = reader.readLine(); // Next line for student number

            // If statement that checks the conditions are met for validation of fistName, secondName, classNumbers, and studentNumber
            // If the conditions are met a Student object is created with the provided data
            if (parts.length == 2 && parts[0].matches("[a-zA-Z]+") && parts[1].matches("[a-zA-Z0-9]+")
                    && classNumberLine.matches("\\d+") && classNumberLine.length() == 1
                    && studentNumberLine.matches("\\d{2}[a-zA-Z]{2,}[0-9]+") && studentNumberLine.length() >= 6) {
                Student student = new Student();
                student.setFirstName(parts[0]);
                student.setLastName(parts[1]);
                student.setClassNumber(Integer.parseInt(classNumberLine.trim()));
                student.setStudentNumber(studentNumberLine.trim());
                students.add(student);
            } 
            // Else statement that prints an error message in red
            else {
                System.out.println();
                System.out.println("\u001B[31m!!!!! - Invalid data found in file: " + nameLine);
            }
        }
    } 
    // This catches and handles IOException exceptions that might occur during file writing, and displaying an error message in red 
    catch (IOException e) {
        System.out.println();
        System.err.println("\u001B[31m!!!!!! - An error occurred while reading the file: " + e.getMessage());
    }
    // Exits the method and returns a list of Student objects
    return students;
    }
}
