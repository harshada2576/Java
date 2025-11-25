import p2.*;

public class VehicleRentalSystem {

    public static void main(String[] args) {

        // Creating a Car object
        Car car = new Car("C101", "Maruti Suzuki", 1500.0, 5);
        System.out.println("----- Car Details -----");
        car.displayDetails();
        System.out.println("Rental Cost for 5 days: ₹" + car.calculateRentalCost(5));

        System.out.println();

        // Creating a LuxuryCar object
        LuxuryCar luxuryCar = new LuxuryCar("L501", "Mercedes-Benz", 5000.0, 4, true);
        System.out.println("----- Luxury Car Details -----");
        luxuryCar.displayDetails();
        System.out.println("Rental Cost for 5 days: ₹" + luxuryCar.calculateRentalCost(5));
    }
}
