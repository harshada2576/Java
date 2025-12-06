import java.util.Scanner;
import java.util.HashSet;

// Custom Exception 
class DuplicateVoteException extends Exception {
    public DuplicateVoteException(String message) {
        super(message);
    }
}

// VotingSystem Class
class VotingSystem {
    private static final HashSet<String> votedUsers = new HashSet<>();

    public VotingSystem() {
        votedUsers.add("U001");
        votedUsers.add("U002");
        votedUsers.add("U003"); // Users who have already voted
    }

    public void castVote(String userId) throws DuplicateVoteException {
        if (votedUsers.contains(userId)) {
            throw new DuplicateVoteException("You have already voted! Duplicate voting is not allowed.");
        }
        votedUsers.add(userId);
        System.out.println("Vote cast successfully!");
    }
}

// Main Class
public class DVException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VotingSystem voting = new VotingSystem();

        System.out.print("Enter your User ID to vote: ");
        String userId = sc.next();

        try {

            voting.castVote(userId);
        } catch (DuplicateVoteException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
