import java.util.Scanner;

// Custom Exception 
class InvalidTicketIDException extends Exception {
    public InvalidTicketIDException(String message) {
        super(message);
    }
}

// TicketBooking Class
class TicketBooking {
    private static final String[] validTicketIDs = { "T1001", "T1002", "T1003" };

    public void bookTicket(String ticketId) throws InvalidTicketIDException {
        boolean isValid = false;
        for (String id : validTicketIDs) {
            if (id.equals(ticketId)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new InvalidTicketIDException("Invalid Ticket ID! Please enter a correct ID.");
        }
        System.out.println("Ticket booked successfully! Ticket ID: " + ticketId);
    }
}

// Main Class
public class InvalidTicketDException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketBooking booking = new TicketBooking();

        System.out.print("Enter Ticket ID: ");
        String ticketId = sc.next();

        try {
            booking.bookTicket(ticketId);
        } catch (InvalidTicketIDException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}