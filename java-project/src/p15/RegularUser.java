package p15;

public class RegularUser extends User {
    private int postCount;

    public RegularUser(String username, String email, int postCount) {
        super(username, email);
        this.postCount = postCount;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Posts: " + postCount);
    }
}
