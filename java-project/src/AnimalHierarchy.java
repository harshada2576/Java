import p8.*; 
import java.util.Scanner; 
 
public class AnimalHierarchy { 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
 
        System.out.print("Enter Dog's Name: "); 
        String dogName = scanner.nextLine(); 
        System.out.print("Enter Dog's Sound: "); 
        String dogSound = scanner.nextLine(); 
        System.out.print("Enter Dog's Breed: "); 
        String breed = scanner.nextLine(); 
        Dog dog = new Dog(dogName, dogSound, 
breed); 
 
        System.out.print("\nEnter Cat's Name: "); 
        String catName = scanner.nextLine(); 
        System.out.print("Enter Cat's Sound: "); 
        String catSound = scanner.nextLine(); 
        System.out.print("Enter Cat's Color: "); 
        String color = scanner.nextLine(); 
        Cat cat = new Cat(catName, catSound, color); 
 
        System.out.println("\nAnimal Sounds:"); 
        dog.makeSound(); 
        System.out.println(); 
        cat.makeSound(); 
 
        scanner.close(); 
    } 
}