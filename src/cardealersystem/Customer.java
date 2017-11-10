package cardealersystem;

import java.util.ArrayList;
import java.util.List;

public class Customer extends PersonBase {
    public int customerID;
    public String phoneNumber;
    public String email;
    public List<Purchase> purchases = new ArrayList<Purchase>();

    public Customer(int id, String first, String last, String add, String phoneNumber, String email) {
        super(first, last, add);
        this.customerID = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
