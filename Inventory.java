
/**
 * For managing inventory
 *
 * Amka Baasanbat
 * 10/11/2024
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;

class Inventory {
    private ArrayList<FoodItem> items;
    private static final String FILE_PATH = "inventory.txt";

    public Inventory() {
        this.items = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0];
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                int quantity = Integer.parseInt(data[3]);
                if (type.equals("Generic")) {
                    items.add(new FoodItem(name, price, quantity));
                } else if (type.equals("Branded") && data.length == 5) {
                    String brand = data[4];
                    items.add(new BrandedFoodItem(name, price, quantity, brand));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (FoodItem item : items) {
                writer.write(item.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public ArrayList<FoodItem> getItems() {
        return items;
    }
    
    public void sortByPrice() {
        Collections.sort(items, Comparator.comparingDouble(FoodItem::getPrice));
    }
    
    public void sortByName() {
        Collections.sort(items, Comparator.comparing(FoodItem::getName));
    }

    public void addItem(FoodItem item) {
        items.add(item);
    }

    public boolean removeItem(String name) {
        return items.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }

    public FoodItem searchItem(String name) {
        for (FoodItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}


// Search function not working correctly, debug search item and fix AIRecommender