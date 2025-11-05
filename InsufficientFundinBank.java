import java.util.Scanner;
class InsufficientFundException extends Exception {
    public InsufficientFundException(String message) {
        super(message);
    }
}
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void withdraw(double amount) throws InsufficientFundException {
        if (amount > balance) {
            throw new InsufficientFundException("Insufficient funds for withdrawal of " + amount);
        }
        balance -= amount;
        System.out.println("Withdrawal of " + amount + " successful. New balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}
public class InsufficientFundinBank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(500.0); // Initial balance of 500

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        try {
            account.withdraw(amount);
        } catch (InsufficientFundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Current balance: " + account.getBalance());
            scanner.close();
        }
    }
}