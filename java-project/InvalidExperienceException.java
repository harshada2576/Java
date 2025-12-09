import java.util.Scanner;

// Custom Exception 
class InvalidExperienceExcep extends Exception {
    public InvalidExperienceExcep(String message) {
        super(message);
    }
}

// JobApplication Class
class JobApplication {
    public void applyJob(int experience) throws InvalidExperienceExcep {
        if (experience < 0 || experience > 50) {
            throw new InvalidExperienceExcep("Invalid experience! Enter a value between 0 and 50.");
        }
        System.out.println("Application submitted successfully with " + experience + " years of experience.");
    }
}

// Main Class
public class InvalidExperienceException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JobApplication job = new JobApplication();

        System.out.print("Enter years of experience: ");
        int experience = sc.nextInt();

        try {
            job.applyJob(experience);
        } catch (InvalidExperienceExcep e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}