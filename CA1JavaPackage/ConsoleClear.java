// Author: Angus Friel
// Student Number: sba22066
// GitHub: https://github.com/Ajfriel86/CA1JavaPackage
// Deployed site:

//Declaring package
package CA1JavaPackage;

public class ConsoleClear {
    // Define a public class named ConsoleClear
    public static void clearConsole() {
        try {
            // Check the operating system
            String os = System.getProperty("os.name").toLowerCase();
    
            if (os.contains("win")) {
                // For Windows: Create a ProcessBuilder to run the "cls" command
                ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "cls");
                
                // Inherit input/output streams and start the process, waiting for it to complete
                builder.inheritIO().start().waitFor();
            } else {
                // For Unix/Linux/macOS: Create a ProcessBuilder to run the "clear" command
                ProcessBuilder builder = new ProcessBuilder("clear");
                
                // Inherit input/output streams and start the process, waiting for it to complete
                builder.inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }
}
