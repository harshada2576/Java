package p14;

abstract class MenuItem {
    protected String name;
    protected double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    abstract int getPreparationTime();

    public void displayDetails() {
        System.out.println(
                "Name: " + name + ", Price: " + price + ", Preparation Time: " + getPreparationTime() + " min");
    }
}