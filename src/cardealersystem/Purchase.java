package cardealersystem;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    public int orderID;
    public int customerID;
    public List<Item> items = new ArrayList<Item>();

    public Purchase(int orderId, int customerId) {
        this.orderID = orderId;
        this.customerID = customerId;;
    }

    public double getTotalCost() {
        if (items.isEmpty()) {
            return 0.0;
        }

        double cost = 0.0;

        for (Item i :
                items) {
            cost += i.price;
        }

        return cost;
    }
}
