
/**
 * For simulating AI/ML by recommending best way to search using if state. Contains all the sort and search methods
 *
 * Amka Baasanbat
 * 12/2/2024 *Newest version*
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;

class AISortingAndSearchingAdvisor {
    public void recommendSortingAlgorithm(Inventory inventory) {
        ArrayList<FoodItem> items = inventory.getItems();
        System.out.println("Analyzing dataset for sorting...");
        if (items.size() <= 10) {
            System.out.println("AI Recommendation: Use Insertion Sort (best for small datasets).");
            insertionSort(items);
        } else if (isNearlySorted(items)) {
            System.out.println("AI Recommendation: Use Bubble Sort (adaptive for nearly sorted datasets).");
            bubbleSort(items);
        } else {
            System.out.println("AI Recommendation: Use Merge Sort (efficient for large datasets).");
            mergeSort(items, 0, items.size() - 1);
        }
    }

    public void recommendSearchingAlgorithm(Inventory inventory, String searchTerm) {
        ArrayList<FoodItem> items = inventory.getItems();
        System.out.println("Analyzing dataset for searching...");
        if (isSorted(items)) {
            System.out.println("AI Recommendation: Use Binary Search.");
            int index = binarySearch(items, searchTerm);
            if (index != -1) {
                System.out.println("Item found: " + items.get(index));
            } else {
                System.out.println("Item not found.");
            }
        } else {
            System.out.println("AI Recommendation: Use Linear Search.");
            linearSearch(items, searchTerm);
        }
    }

    private boolean isNearlySorted(ArrayList<FoodItem> items) {
        int outOfOrderCount = 0;
        for (int i = 1; i < items.size(); i++) {
            if (items.get(i - 1).getName().compareTo(items.get(i).getName()) > 0) {
                outOfOrderCount++;
            }
            if (outOfOrderCount > 2) {
                return false;
            }
        }
        return true;
    }

    public boolean isSorted(ArrayList<FoodItem> items) {
        for (int i = 1; i < items.size(); i++) {
            if (items.get(i - 1).getName().compareTo(items.get(i).getName()) > 0) {
                return false;
            }
        }
        return true;
    }

    private void insertionSort(ArrayList<FoodItem> items) {
        for (int i = 1; i < items.size(); i++) {
            FoodItem key = items.get(i);
            int j = i - 1;
            while (j >= 0 && items.get(j).getName().compareTo(key.getName()) > 0) {
                items.set(j + 1, items.get(j));
                j--;
            }
            items.set(j + 1, key);
        }
    }

    private void bubbleSort(ArrayList<FoodItem> items) {
        int n = items.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (items.get(j).getName().compareTo(items.get(j + 1).getName()) > 0) {
                    Collections.swap(items, j, j + 1);
                }
            }
        }
    }

    private void mergeSort(ArrayList<FoodItem> items, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(items, left, mid);
            mergeSort(items, mid + 1, right);
            merge(items, left, mid, right);
        }
    }

    private void merge(ArrayList<FoodItem> items, int left, int mid, int right) {
        ArrayList<FoodItem> leftList = new ArrayList<>(items.subList(left, mid + 1));
        ArrayList<FoodItem> rightList = new ArrayList<>(items.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).getName().compareTo(rightList.get(j).getName()) <= 0) {
                items.set(k++, leftList.get(i++));
            } else {
                items.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            items.set(k++, leftList.get(i++));
        }
        while (j < rightList.size()) {
            items.set(k++, rightList.get(j++));
        }
    }

     public void linearSearch(ArrayList<FoodItem> items, String searchTerm) {
        for (FoodItem item : items) {
            if (item.getName().equalsIgnoreCase(searchTerm)) {
                System.out.println("Item found: " + item);
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public int binarySearch(ArrayList<FoodItem> items, String searchTerm) {
        int left = 0, right = items.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = items.get(mid).getName().compareToIgnoreCase(searchTerm);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
// 