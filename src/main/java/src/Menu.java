package src;

import java.util.Scanner;

public class Menu {

    public static void startMenu(){
        String currentRole =  Roles.getRole();
        Scanner sc = new Scanner(System.in);
        int id = SQL.getID();

        while(true){
            if(currentRole.equals("user")){
                System.out.println("Current Role: " + currentRole);
                System.out.println("What would you like to do? (Search or Leave)");
                String choice = sc.nextLine().trim().toLowerCase();
                if(choice.equals("Leave")){
                    System.out.println("Goodbye!");
                    break;
                }else if(choice.equals("Search")){
                    System.out.println("Would you like to search for a product by name or location?");
                    String type = sc.nextLine();
                    System.out.println("What is it you want to search for?");
                    String searchItem = sc.nextLine();

                    SQL.Search(type, searchItem);
                }
                else{
                    System.out.println("Invalid Choice");
                }

            }
            if(currentRole.equals("admin")){
                System.out.println("What would you like to do to the inventory? (Create, Update, Search, or Leave?)");

                String choice = sc.nextLine().trim().toLowerCase();


                if (choice.equalsIgnoreCase("Leave")) {
                    break;
                }
                else if (choice.equalsIgnoreCase("Create")) {
                    id += 1;
                    System.out.println("Enter the name of the product");
                    String name = sc.nextLine();
                    System.out.println("Enter the price of the product");
                    Double price = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter the quantity of the product");
                    Integer quantity = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the location of the product");
                    String location = sc.nextLine();

                    Product p;

                    if (location.equalsIgnoreCase("Grocery")){
                        System.out.println("Enter the expiration date of the product {YYYY-MM-DD}: ");
                        String expiration = sc.nextLine();
                        System.out.println("Enter the sell date of the product {YYYY-MM-DD}: ");
                        String sellDate = sc.nextLine();

                        p = new Perishable(id, name, price, quantity, location, expiration, sellDate);
                    }
                    else if (location.equalsIgnoreCase("Beauty")){
                        System.out.println("Enter the description of the product");
                        String description = sc.nextLine();
                        p = new Description(id, name, price, quantity, location, description);
                    }

                    else {
                        p = new Product(id, name, price, quantity, location);
                    }
                    SQL.insertProduct(p);
                }
                else if (choice.equalsIgnoreCase("Update")) {

                    System.out.println("What would you like to update? (Price or Quantity)");
                    String update = sc.nextLine();

                    if (update.equalsIgnoreCase("Quantity")) {
                        System.out.println("Add or Subtract?");
                        String operation = sc.nextLine();
                        if (operation.equalsIgnoreCase("Subtract")) {
                            System.out.println("What would you like to remove from the inventory?");
                            String subtractItem = sc.nextLine();
                            System.out.println("How many items do you want to remove?");
                            int subtractItemQuantity = sc.nextInt();
                            SQL.decrementQuality(subtractItem, subtractItemQuantity);
                        }
                        if (operation.equalsIgnoreCase("Add")) {
                            System.out.println("What would you like to add to the inventory?");
                            String addItem = sc.nextLine();
                            System.out.println("How many items do you want to add?");
                            int addedItemQuantity = sc.nextInt();
                            SQL.increaseQuality(addItem, addedItemQuantity);
                        }
                    }
                    if(update.equalsIgnoreCase("Price")) {
                        System.out.println("What item would you like to change the price?");
                        String changeItem = sc.nextLine();
                        System.out.println("What price do you want to change the item to?");
                        double changeItemPrice = sc.nextDouble();
                        SQL.changePrice(changeItem, changeItemPrice);
                    }

                }
                else if (choice.equalsIgnoreCase("Search")) {
                    System.out.println("Would you like to search for a product by name or location?");
                    String type = sc.nextLine();
                    System.out.println("What is it you want to search for?");
                    String searchItem = sc.nextLine();

                    SQL.Search(type, searchItem);
                }
            }
        }
    }
}