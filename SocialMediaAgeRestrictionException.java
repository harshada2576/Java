import java.util.Scanner;

// Custom Exception 
class AgeRestrictionException extends Exception {
    public AgeRestrictionException(String message) {
        super(message);
    }
}

// UserRegistration Class
class UserRegistration {
    public void registerUser(int age) throws AgeRestrictionException {
        if (age < 13) {
            throw new AgeRestrictionException("You must be at least 13 years old to register!");
        }
        System.out.println("Registration successful!");
    }
}

// Main Class
public class SocialMediaAgeRestrictionException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserRegistration registration = new UserRegistration();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        try {
            registration.registerUser(age);
        } catch (AgeRestrictionException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}