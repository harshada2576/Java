import java.util.Scanner;
import java.util.HashSet;

// Custom Exception 
class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}

// Inventory Class
class Inventory {
    private static final HashSet<String> availableItems = new HashSet<>();

    public Inventory() {
        availableItems.add("I101");
        availableItems.add("I102");
        availableItems.add("I103"); // Available items
    }

    public void purchaseItem(String itemId) throws OutOfStockException {
        if (!availableItems.contains(itemId)) {
            throw new OutOfStockException("Item out of stock! Please choose another item.");
        }
        availableItems.remove(itemId);
        System.out.println("Item " + itemId + " purchased successfully!");
    }
}

// Main Class
public class InventoryManagementOutOfStockException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        System.out.print("Enter Item ID to purchase: ");
        String itemId = sc.next();

        try {
            inventory.purchaseItem(itemId);
        } catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}