package p14;

public class MainCourse extends MenuItem {
    public MainCourse(String name, double price) {
        super(name, price);
    }

    @Override
    public int getPreparationTime() {
        return 20;
    }
}