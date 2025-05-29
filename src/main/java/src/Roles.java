package src;

public class Roles {
    private String username;
    private static String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Roles(String username, String role) {
        this.username = username;
        this.role = role;
    }


}
