import java.util.Scanner;

// Custom Exception 
class InvalidDeliveryException extends Exception {
    public InvalidDeliveryException(String message) {
        super(message);
    }
}

// FoodDelivery Class
class FoodDelivery {
    private static final String[] validAddresses = { "Downtown", "Suburb", "City Center" };

    public void placeOrder(String address) throws InvalidDeliveryException {
        boolean isValid = false;
        for (String validAddress : validAddresses) {
            if (validAddress.equalsIgnoreCase(address)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new InvalidDeliveryException("Invalid delivery address! Choose a valid location.");
        }
        System.out.println("Order placed successfully! Delivery to: " + address);
    }
}

// Main Class
public class InvalidDeliveryAddressException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodDelivery delivery = new FoodDelivery();

        System.out.print("Enter delivery address: ");
        String address = sc.next();

        try {
            delivery.placeOrder(address);
        } catch (InvalidDeliveryException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}