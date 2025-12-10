package p14;

public class Starter extends MenuItem {
    public Starter(String name, double price) {
        super(name, price);
    }

    @Override
    public int getPreparationTime() {
        return 10;
    }
}