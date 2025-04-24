
/**
 * User who can search for items.
 *
 * Amka Baasanbat
 * 10/12/2024
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

class RegularUser extends User {
    public RegularUser(Inventory inventory) {
        super(inventory);
    }

    @Override
    public void displayMenu(Scanner scanner) {
        while (true) {
            System.out.println("User Mode: 1. View All Items 2. Search Item 3. Back");
            int choice = InventorySystem.getValidInt(scanner);

            if (choice == 1) {
                viewAllItems();
            } else if (choice == 2) {
                searchItem(scanner);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
