package src;

import src.RoleSQL;
import src.Roles;

import java.util.Scanner;

public class User {

    public static Roles Login() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter username: ");
            String inputUsername = sc.nextLine();
            System.out.println("Enter password: ");
            String inputPassword = sc.nextLine();

            String role = RoleSQL.checkUser(inputUsername, inputPassword);

            if (role != null) {
                System.out.println("Welcome to the Inventory Manager as " + role);
                return new Roles(inputUsername, role);
            } else {
                System.out.println("Invalid username or password");
            }
        }
    }
}
