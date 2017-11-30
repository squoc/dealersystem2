package cardealersystem;

public class ClientDisplay {

    public static void displayMainMenu() {
        System.out.println("1. Check car inventory");
        System.out.println("2. Check part inventory");
        System.out.println("3. Check sale data");
        System.out.println("4. Exit");
    }

    public static void displayCarSubMenu() {
        System.out.println("1. Get all car");
        System.out.println("2. Find cars by make");
        System.out.println("3. Find car by VIN");
        System.out.println("4. Add new car into inventory");
        System.out.println("5. Go back");
    }

    public static void displayPartSubMenu() {
        System.out.println("1. Get all parts");
        System.out.println("2. Find part by ID");
        System.out.println("3. Add new part");
        System.out.println("4. Go back");
    }

    public static void displaySaleSubMenu() {
        System.out.println("1. Get all monthly sales");
        System.out.println("2. Go back");
    }
}
