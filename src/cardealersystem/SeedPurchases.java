package cardealersystem;

import java.util.ArrayList;
import java.util.List;

public class SeedPurchases {
    public static List<Purchase> purchases = new ArrayList<Purchase>();

    public static void seed() {
        Purchase p1 = new Purchase(1, 111);
        Part part1 = new Part(1, "Air Filter", 25.0, "China");
        p1.items.add(part1);
        Part part2 = new Part(2, "Brake Rotor", 217, "US");
        p1.items.add(part2);
        Part part3 = new Part(3, "Radiator Hose", 20, "China");
        p1.items.add(part3);
        purchases.add(p1);

        Purchase p2 = new Purchase(2, 222);
        Part part4 = new Part(2, "Brake Rotor", 217, "US");
        p2.items.add(part4);
        Part part5 = new Part(3, "Radiator Hose", 20, "China");
        p2.items.add(part5);
        purchases.add(p2);

        Purchase p3 = new Purchase(3, 333);
        p3.items.add(new Part(4, "Coolant", 12, "US"));
        p3.items.add(new Part(5, "Battery", 120, "US"));
        purchases.add(p3);
    }

    public static String getPurchasesInfo() {
        String sales = "";
        for (Purchase p :
                purchases) {
            sales += p.getPurchaseInfo();
        }

        return sales;
    }

    public static double getPurchasesCost() {
        double total = 0.0;
        for (Purchase p :
                purchases) {
            total += p.getPurchaseCost();
        }

        return total;
    }
}
