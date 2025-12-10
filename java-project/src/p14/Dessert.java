package p14;

public class Dessert extends MenuItem {
    public Dessert(String name, double price) {
        super(name, price);
    }

    @Override
    public int getPreparationTime() {
        return 5;
    }
}