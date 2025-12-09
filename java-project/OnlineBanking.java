import java.util.Scanner;

// Custom Exception 
class InvalidAccountTypeException extends Exception {
    public InvalidAccountTypeException(String message) {
        super(message);
    }
}

// BankAccount Class
class BankAccount {
    private static final String[] validAccountTypes = { "Savings", "Current", "Fixed Deposit" };

    public void createAccount(String accountType) throws InvalidAccountTypeException {
        boolean isValid = false;
        for (String type : validAccountTypes) {
            if (type.equalsIgnoreCase(accountType)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new InvalidAccountTypeException("Invalid account type! Choose Savings, Current, or Fixed Deposit.");
        }
        System.out.println(accountType + " account created successfully!");
    }
}

// Main Class
public class OnlineBanking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount bank = new BankAccount();

        System.out.print("Enter account type (Savings, Current, Fixed Deposit): ");
        String accountType = sc.next();

        try {
            bank.createAccount(accountType);
        } catch (InvalidAccountTypeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}