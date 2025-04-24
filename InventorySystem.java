
/**
 *Use this main class to run the program
 *
 * Amka Baasanbat
 * 12/3/2024 Newest Version
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventorySystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose mode: 1. Manager 2. User 3. Exit");
            int choice = getValidInt(scanner);

            if (choice == 1) {
                Manager manager = new Manager(inventory);
                manager.displayMenu(scanner);
            } else if (choice == 2) {
                RegularUser user = new RegularUser(inventory);
                user.displayMenu(scanner);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        inventory.saveToFile();
        scanner.close();
    }

    // Validate integer input without a message
    public static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume invalid input
            }
        }
    }

    // Validate integer input with a custom message
    public static int getValidInt(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume invalid input
            }
        }
    }

    // Validate double input with a custom message
    public static double getValidDouble(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                scanner.next(); // Consume invalid input
            }
        }
    }
}
