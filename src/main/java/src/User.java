package src;

import java.util.Scanner;

public class User {
    static String correctUsername = Constants.getUsername();
    static String correctUserPassword = Constants.getUserPassword();
    static String correctAdminPassword = Constants.getAdminPassword();
    static String correctAdminusername = Constants.getAdminUsername();

    public static Roles Login() {
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("Enter username: ");
            String inputUsername = sc.nextLine();
            System.out.println("Enter password: ");
            String inputUserPassword = sc.nextLine();

            if (inputUsername.equals(correctUsername) && inputUserPassword.equals(correctUserPassword)) {
                System.out.println("Welcome to the Inventory Manager");
                return new Roles(inputUsername, "user");


            } else if ((inputUsername.equals(correctAdminusername) && inputUserPassword.equals(correctAdminPassword))){
                System.out.println("Welcome to the Inventory Manager");
                return new Roles(inputUsername, "admin");
            }
            else {
                System.out.println("Invalid username or password");
                return null;
            }
        }
    }
}
