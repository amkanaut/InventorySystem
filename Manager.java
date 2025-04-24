
/**
 * Manager mode
 *
 * Amka Baasanbat   
 * 12/3/2024 Newest Version
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

class Manager extends User {
    private AISortingAndSearchingAdvisor aiAdvisor;

    public Manager(Inventory inventory) {
        super(inventory);
        this.aiAdvisor = new AISortingAndSearchingAdvisor(); // Initialize AI Advisor
    }

    @Override
    public void displayMenu(Scanner scanner) {
        while (true) {
            System.out.println("Manager Mode:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Sort Items (AI Recommendation)");
            System.out.println("4. Search Items (AI Recommendation)");
            System.out.println("5. Binary Search");
            System.out.println("6. Linear Search");
            System.out.println("7. View All Items");
            System.out.println("8. Back");
            
            int choice = InventorySystem.getValidInt(scanner);

            if (choice == 1) {
                addItem(scanner);
            } else if (choice == 2) {
                removeItem(scanner);
            } else if (choice == 3) {
                aiAdvisor.recommendSortingAlgorithm(inventory); // AI sorting recommendation
            } else if (choice == 4) {
                System.out.println("Enter the name of the item to search:");
                scanner.nextLine(); // Consume newline
                String searchTerm = scanner.nextLine();
                aiAdvisor.recommendSearchingAlgorithm(inventory, searchTerm); // AI searching recommendation
            } else if (choice == 5) {
                binarySearch(scanner);
            } else if (choice == 6) {
                linearSearch(scanner);
            } else if (choice == 7) {
                viewAllItems();
            } else if (choice == 8) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void binarySearch(Scanner scanner) {
        if (!aiAdvisor.isSorted(inventory.getItems())) {
            System.out.println("The dataset is not sorted. Binary Search cannot be used.");
            return;
        }
        System.out.println("Enter the name of the item to search:");
        scanner.nextLine(); // Consume newline
        String searchTerm = scanner.nextLine();
        int index = aiAdvisor.binarySearch(inventory.getItems(), searchTerm);
        if (index != -1) {
            System.out.println("Item found: " + inventory.getItems().get(index));
        } else {
            System.out.println("Item not found.");
        }
    }

    private void linearSearch(Scanner scanner) {
        System.out.println("Enter the name of the item to search:");
        scanner.nextLine(); // Consume newline
        String searchTerm = scanner.nextLine();
        aiAdvisor.linearSearch(inventory.getItems(), searchTerm); // Use linear search
    }

    private void addItem(Scanner scanner) {
        System.out.println("Enter item type: 1. Generic 2. Branded");
        int type = InventorySystem.getValidInt(scanner);

        System.out.println("Enter name:");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        double price = InventorySystem.getValidDouble(scanner, "Enter price:");
        int quantity = InventorySystem.getValidInt(scanner, "Enter quantity:");

        if (type == 1) {
            FoodItem item = new FoodItem(name, price, quantity);
            inventory.addItem(item);
        } else if (type == 2) {
            System.out.println("Enter brand:");
            scanner.nextLine(); // Consume newline
            String brand = scanner.nextLine();
            BrandedFoodItem brandedItem = new BrandedFoodItem(name, price, quantity, brand);
            inventory.addItem(brandedItem);
        }
        System.out.println("Item added.");
    }

    private void removeItem(Scanner scanner) {
        System.out.println("Enter the name of the item to remove:");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        boolean removed = inventory.removeItem(name);
        if (removed) {
            System.out.println("Item removed.");
        } else {
            System.out.println("Item not found.");
        }
    }
}

