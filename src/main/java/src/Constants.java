package src;

public class Constants {
    private static String dbPath = "jdbc:sqlite:inventory.db";

    private static String userPassword;
    private static String adminPassword;
    private static String username;
    private static String adminUsername;

    public static String getAdminPassword() {
        return adminPassword;
    }

    public static void setAdminPassword(String adminPassword) {
        Constants.adminPassword = adminPassword;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserPassword(String userPassword) {
        Constants.userPassword = userPassword;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Constants.username = username;
    }

    public static String getAdminUsername() {
        return adminUsername;
    }

    public static void setAdminUsername(String adminUsername) {
        Constants.adminUsername = adminUsername;
    }




    public static String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public Constants(String dbPath) {
        this.dbPath = dbPath;
        this.userPassword = userPassword;
        this.adminPassword = adminPassword;
        this.username = username;
        this.adminUsername = adminUsername;
    }
}
