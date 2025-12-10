package p15;

public class AdminUser extends User {
    private int adminLevel;

    public AdminUser(String username, String email,
            int adminLevel) {
        super(username, email);
        this.adminLevel = adminLevel;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Admin Level: " +
                adminLevel);
    }
}
