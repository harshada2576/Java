import p15.*;
import java.util.Scanner;

public class SocialMediaPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Regular User's Username: ");
        String regUsername = scanner.nextLine();
        System.out.print("Enter Regular User's Email: ");
        String regEmail = scanner.nextLine();
        System.out.print("Enter Number of Posts: ");
        int posts = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        RegularUser regularUser = new RegularUser(regUsername, regEmail, posts);

        System.out.print("\nEnter Admin User's Username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter Admin User's Email: ");
        String adminEmail = scanner.nextLine();
        System.out.print("Enter Admin Level: ");
        int adminLevel = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        AdminUser adminUser = new AdminUser(adminUsername, adminEmail, adminLevel);

        System.out.println("\nUser Details:");
        regularUser.displayDetails();
        System.out.println();
        adminUser.displayDetails();

        scanner.close();
    }
}