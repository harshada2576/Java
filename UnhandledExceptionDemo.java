public class UnhandledExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Program started");

        // This will throw an ArithmeticException (division by zero)
        int result = 10 / 0;

        System.out.println("Result: " + result); // This line is never reached
        System.out.println("Program ended");
    }
}