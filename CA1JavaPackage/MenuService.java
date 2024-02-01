// Author: Angus Friel
// Student Number: sba22066
// GitHub:
// Deployed site:

// Declare package
package CA1JavaPackage;

// Importing necessary Java libraries
import java.util.Scanner;

// Define a public class named MenuService
public class MenuService {

    // This method prompts the user to return to the main menu and handles their choice.
    // It returns 'true' if the user chooses not to return to the main menu (exit the program).
    // It returns 'false' if the user chooses to return to the main menu.
    public static boolean promptReturnToMenu(Scanner scanner) {
        System.out.println();
        System.out.print("\u001B[34mDo you want to return to the main menu? (Y/N): ");
        String returnToMenuChoice = scanner.nextLine();
        
        if ("N".equalsIgnoreCase(returnToMenuChoice)) {
            return true; // Exit the program
        }
        
        ConsoleClear.clearConsole();
        return false; // Return to the main menu
    }
}
