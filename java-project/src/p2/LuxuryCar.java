package p2;

public class LuxuryCar extends Car {
    private boolean hasSunroof;

    public LuxuryCar(String vehicleId, String brand,
            double rentalPricePerDay, int numberOfSeats,
            boolean hasSunroof) {
        super(vehicleId, brand, rentalPricePerDay,
                numberOfSeats);
        this.hasSunroof = hasSunroof;
    }

    @Override
    public double calculateRentalCost(int days) {
        double baseCost = rentalPricePerDay * days;
        if (hasSunroof) {
            baseCost += 1000; // extra luxury charge
        }
        return baseCost;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Sunroof: " + (hasSunroof ? "Yes" : "No"));
    }
}
