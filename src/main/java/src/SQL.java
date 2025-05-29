package src;

import java.sql.*;
import java.util.List;

public class SQL {

    public static void insertProduct(Product product) {
        String url = Constants.getDbPath();


        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sql = "INSERT INTO warehouse (id, name, price, quantity, location, description, expirationDate, sellDate) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, product.getId());
                    pstmt.setString(2, product.getName());
                    pstmt.setDouble(3, product.getPrice());
                    pstmt.setInt(4, product.getQuantity());
                    pstmt.setString(5, product.getLocation());

                    if (product instanceof Perishable) {
                        Perishable p = (Perishable) product;
                        pstmt.setString(7, p.getExpirationDate());
                        pstmt.setString(8, p.getSellDate());
                    } else {
                        pstmt.setNull(7, java.sql.Types.VARCHAR);
                        pstmt.setNull(8, java.sql.Types.VARCHAR);
                    }

                    if (product instanceof Description) {
                        Description p = (Description) product;
                        pstmt.setString(6, p.getDescription());
                    } else{
                        pstmt.setNull(6, java.sql.Types.VARCHAR);
                    }

                    pstmt.executeUpdate();
                    System.out.println("Item Inserted");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int getID() {
        String url = Constants.getDbPath();
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sql = "SELECT MAX(id) AS max_id FROM warehouse";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int currentID = rs.getInt("max_id");
                    return currentID;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public static int getQuantity(String name) {
        String url = Constants.getDbPath();
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sql = "SELECT name, quantity FROM warehouse";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                if (rs.next() && rs.getString("name").equals(name)) {
                    int currentQuality = rs.getInt("quantity");
                    return currentQuality;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;}

    public static void increaseQuality(String name, int requestQuantity){
        String url = Constants.getDbPath();
        boolean activeItem = itemExists(name);

        if(activeItem == false){
            System.out.println("Item does not exist");
        }else{try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sql = "UPDATE warehouse SET quantity = quantity + ? WHERE name = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, requestQuantity);
                stmt.setString(2, name);
                int rs = stmt.executeUpdate();
                System.out.println("Successfully added " + requestQuantity + " quantity to " + name + ". The current quantity of " + name + " is: " + SQL.getQuantity(name) + ".");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }

    }


    public static void decrementQuality(String name, int requestQuantity){
        String url = Constants.getDbPath();

        int currentQuantity = getQuantity(name);
        boolean activeItem = itemExists(name);

        if (activeItem == false) {
            System.out.println("Item does not exist");
        }
        else{

            if(currentQuantity <= requestQuantity){
                System.out.println("ERROR: Quantity exceeds request");
            }
            else
            {try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    String sql = "UPDATE warehouse SET quantity = quantity - ? WHERE name = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, requestQuantity);
                    stmt.setString(2, name);
                    int rs = stmt.executeUpdate();
                    System.out.println("Successfully removed " + requestQuantity + " quantity from " + name + ". The current quantity of " + name + " is: " + SQL.getQuantity(name) + ".");

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            }

        }
    }

    public static void changePrice(String name, double requestPrice){
        String url = Constants.getDbPath();
        double minPrice = 0.01;
        boolean activeItem = itemExists(name);

        if(activeItem == false){
            System.out.println("Item does not exist");
        }
        else{try (Connection conn = DriverManager.getConnection(url)) {
            if(requestPrice < minPrice){
                System.out.println("ERROR: Price can not be less than minimum price");
            } else{
                if (conn != null) {
                    String sql = "UPDATE warehouse SET price = ? WHERE name = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setDouble(1, requestPrice);
                    stmt.setString(2, name);
                    int rs = stmt.executeUpdate();
                    System.out.println("Successfully updated " + name + "'s price to " + requestPrice);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);}

        }
    }


    public static void Search(String type, String choice ){
        String url = Constants.getDbPath();

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null){
                if(type.equals("Name")) {
                    String sql = "SELECT * FROM warehouse WHERE name = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, choice);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Price: " + rs.getDouble("price"));
                        System.out.println("Quantity: " + rs.getInt("quantity"));
                        System.out.println("Location: " + rs.getString("location"));
                        System.out.println("Description: " + rs.getString("description"));
                        System.out.println("ExpirationDate: " + rs.getString("expirationDate"));
                        System.out.println("SellDate: " + rs.getString("sellDate"));
                    } else {
                        System.out.println("Item not found");
                    }
                }

            }
            if(type.equals("Location")){ if (conn != null) {
                String sql = "SELECT * FROM warehouse WHERE location = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, choice);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Price: " + rs.getDouble("price"));
                    System.out.println("Quantity: " + rs.getInt("quantity"));
                    System.out.println("Location: " + rs.getString("location"));
                    System.out.println("Description: " + rs.getString("description"));
                    System.out.println("ExpirationDate: " + rs.getString("expirationDate"));
                    System.out.println("SellDate: " + rs.getString("sellDate"));
                } else {
                    System.out.println("Item not found");
                }

            }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public static boolean itemExists(String name){
        String url = Constants.getDbPath();
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String sql = "SELECT * FROM warehouse WHERE name = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }



}

