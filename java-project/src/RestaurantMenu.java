import p14.*;
import java.util.Scanner;

public class RestaurantMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Starter Name: ");
        String starterName = scanner.nextLine();
        System.out.print("Enter Starter Price: ");
        double starterPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        Starter starter = new Starter(starterName,
                starterPrice);

        System.out.print("Enter Main Course Name: ");
        String mainName = scanner.nextLine();
        System.out.print("Enter Main Course Price: ");
        double mainPrice = scanner.nextDouble();
        scanner.nextLine();
        MainCourse mainCourse = new MainCourse(mainName, mainPrice);

        System.out.print("Enter Dessert Name: ");
        String dessertName = scanner.nextLine();
        System.out.print("Enter Dessert Price: ");
        double dessertPrice = scanner.nextDouble();
        Dessert dessert = new Dessert(dessertName, dessertPrice);

        System.out.println("\nMenu Items:");
        starter.displayDetails();
        System.out.println();
        mainCourse.displayDetails();
        System.out.println();
        dessert.displayDetails();

        scanner.close();
    }
}