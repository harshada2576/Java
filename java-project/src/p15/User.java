package p15;

public class User {
    protected String username, email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void displayDetails() {
        System.out.println("Username: " + username +
                ", Email: " + email);
    }
}
