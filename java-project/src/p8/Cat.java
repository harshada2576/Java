package p8;

public class Cat extends Animal {
    private String color;

    public Cat(String name, String sound, String color) {
        super(name, sound);
        this.color = color;
    }

    @Override
    public void makeSound() {
        super.makeSound();
        System.out.println("Color: " + color);
    }
}