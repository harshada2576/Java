import java.util.Scanner;

// Custom Exception 
class InvalidPickupLocationException extends Exception {
    public InvalidPickupLocationException(String message) {
        super(message);
    }
}

// RideBooking Class
class RideBooking {
    private static final String[] validLocations = { "Downtown",
            "Airport", "Suburb" };

    public void bookRide(String pickupLocation) throws InvalidPickupLocationException {
        boolean isValid = false;
        for (String location : validLocations) {
            if (location.equalsIgnoreCase(pickupLocation)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new InvalidPickupLocationException("Invalid pickup location! Please choose a valid location.");
        }
        System.out.println("Ride booked successfully from " +
                pickupLocation + "!");
    }
}

// Main Class
public class invalidpickuplocexcep {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RideBooking ride = new RideBooking();

        System.out.print("Enter your pickup location: ");
        String pickupLocation = sc.next();

        try {
            ride.bookRide(pickupLocation);
        } catch (InvalidPickupLocationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}