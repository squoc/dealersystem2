package cardealersystem;

public class Item {
    public String name;
    public String description;
    public int partNumber;
    public double price;

    public Item() {
        name = "NA";
        description = "NA";
        partNumber = 0;
        price = 0.0;
    }

    public Item(String name, String description, int partNumber, double price) {
        this.name = name;
        this.description = description;
        this.partNumber = partNumber;
        this.price = price;
    }
}
