import java.util.Scanner;

// Custom Exception 
class InvalidAnswerExcep extends Exception {
    public InvalidAnswerExcep(String message) {
        super(message);
    }
}

// Quiz Class
class Quiz {
    private static final char[] validAnswers = { 'A', 'B', 'C', 'D' };

    public void submitAnswer(char answer) throws InvalidAnswerExcep {
        boolean isValid = false;
        for (char validAnswer : validAnswers) {
            if (validAnswer == answer) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new InvalidAnswerExcep("Invalid answer! Choose A, B, C, or D.");
        }
        System.out.println("Answer submitted successfully!");
    }
}

// Main Class
public class InvalidAnswerException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Quiz quiz = new Quiz();

        System.out.print("Enter your answer (A, B, C, or D): ");
        char answer = sc.next().toUpperCase().charAt(0);

        try {
            quiz.submitAnswer(answer);
        } catch (InvalidAnswerExcep e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}