package src;
import java.sql.*;


public class RoleSQL {

    public static void insertInitialAdmin() {
        String url = Constants.getDbPath();
        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sql = "INSERT INTO Login (username, password_hash, expireDate, role) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, "admin");
                    pstmt.setString(2, "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
                    pstmt.setString(3, "2025-12-31");
                    pstmt.setString(4, "admin");
                    pstmt.executeUpdate();
                    System.out.println("Initial Admin Inserted");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createRole(Roles r) {
        String url = Constants.getDbPath();
        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sql = "INSERT INTO Login (username, password_hash, expireDate, role) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, r.getUsername());
                    pstmt.setString(2, r.getPassword());
                    pstmt.setString(3, r.getExpireDate());
                    pstmt.setString(4, r.getRole());
                    pstmt.executeUpdate();
                    System.out.println("New User Inserted Inserted");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String checkUser(String username, String password) {
        String url = Constants.getDbPath();
        String sql = "SELECT role FROM login WHERE username = ? AND password_hash = ?";

        try (var conn = DriverManager.getConnection(url);
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error during login check", e);
        }
    }



    public static void main(String[] args){
            //insertInitialAdmin();

    }
}

