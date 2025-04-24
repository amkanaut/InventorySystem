
/**
 * User class containing DisplayMenu
 *
 * Amka Baasanbat
 * 12/3/2024 * Added new options for display menu to 
 */
import java.util.Scanner;

abstract class User {
    protected Inventory inventory;

    public User(Inventory inventory) {
        this.inventory = inventory;
    }

    public abstract void displayMenu(Scanner scanner);

    public void searchItem(Scanner scanner) {
    System.out.println("Enter the item name to search:");
    scanner.nextLine(); // Clear the buffer before reading input
    String name = scanner.nextLine(); // Read the actual input
    FoodItem item = inventory.searchItem(name);
    if (item != null) {
        System.out.println("Item found: " + item);
    } else {
        System.out.println("Item not found.");
    }
}

    public void viewAllItems() {
        for (FoodItem item : inventory.getItems()) {
            System.out.println(item);
        }
    }
}

