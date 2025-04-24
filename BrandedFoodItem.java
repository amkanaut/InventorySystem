
/**
 * Subclass for branded Food .
 *
 * Amka Baasanbat
 * @10/13/2024
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;

class BrandedFoodItem extends FoodItem {
    private String brand;

    public BrandedFoodItem(String name, double price, int quantity, String brand) {
        super(name, price, quantity);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Brand: " + brand + ", " + super.toString();
    }

    @Override
    public String toCSV() {
        return "Branded," + getName() + "," + getPrice() + "," + getQuantity() + "," + brand;
    }
}
