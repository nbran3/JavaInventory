package src;

public class Roles {

    public Roles(String inputUsername, String role) {
        this.username = inputUsername;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        Roles.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    private String username;
    private static String role;
    private String password;
    private String expireDate;

   

    public Roles(String username, String password, String expireDate, String role) {
        this.username = username;
        this.password = password;
        this.expireDate = expireDate;
        this.role = role;
        
    }


}
