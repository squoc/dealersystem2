package cardealersystem;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    public int orderID;
    public int customerID;
    public List<Part> items = new ArrayList<Part>();

    public Purchase(int orderId, int customerId) {
        this.orderID = orderId;
        this.customerID = customerId;;
    }

    public double getPurchaseCost() {
        if (items.isEmpty()) {
            return 0.0;
        }

        double cost = 0.0;

        for (Part i :
                items) {
            cost += i.price;
        }

        return cost;
    }

    public String getPurchaseInfo() {
        String itemsInfo = "";
        for (Part p :
                items) {
            itemsInfo += p.getPartInfo();
        }
        String info = "Order ID: " + orderID + "\nCustomer ID: " + customerID
                    + "\nList of items purchases: " + itemsInfo + "\n--------\n";

        return info;
    }
}
