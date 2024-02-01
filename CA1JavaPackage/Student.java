// Author: Angus Friel
// Student Number: sba22066
// GitHub: https://github.com/Ajfriel86/CA1JavaPackage

//Declaring package
package CA1JavaPackage;

// This line declares a public class named Student. Being public, it can be accessed from other classes.
public class Student {

        // Defining private variables. These variables are private, meaning they can only be accessed within the Student class.
        private String firstName;
        private String lastName;
        private int classNumber;
        private String studentNumber;
    
        // Setters - These methods allow external code to set the values of the variables. This is part of the encapsulation principle in OOP
        // Setter for firstname
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        
        // Setter for lastname
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    
        // Setter for classNumber
        public void setClassNumber(int classNumber) {
            this.classNumber = classNumber;
        }
        
        // Setter for studentNumber
        public void setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
        }
    
        // Getters - These methods allow external code to retrieve the values of the variables. Again, this is an implementation of encapsulation.
        // Getter for firstname
        public String getFirstName() {
            return firstName;
        }
    
        // Getter for lastname
        public String getLastName() {
            return lastName;
        }
    
        // Getter for classnumber
        public int getClassNumber() {
            return classNumber;
        }
    
        // Getter for srtudentnumber
        public String getStudentNumber() {
            return studentNumber;
        }
    
        // This is a public method that determines the workload of a student based on the classNumber
        public String determineWorkload() {
            if (classNumber == 1) {
                return "very light";
            } else if (classNumber == 2) {
                return "light";
            } else if (classNumber >= 3 && classNumber <= 5) {
                return "Part-Time";
            } else if (classNumber >= 6) {
                return "Full-Time";
            }
            return "Unknown";
        }
    
}
