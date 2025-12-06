package p8;

public class Animal {
    protected String name, sound;

    public Animal(String name, String sound) {
        this.name = name;
        this.sound = sound;
    }

    public void makeSound() {
        System.out.println(name + " makes sound: " +
                sound);
    }
}