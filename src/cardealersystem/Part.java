package cardealersystem;

public class Part {
    public int partID;
    public String description;
    public double price;
    public String origin;

    public Part(int id, String des, double price, String origin) {
        this.partID = id;
        this.description = des;
        this.price = price;
        this.origin = origin;
    }

    public String getPartInfo() {
        String info = "Part ID: " + this.partID + "\nDescription: " + this.description + "\nPrice: " + this.price
                        + "\nOrigin: " + this.origin + "\n---------\n";

        return info;
    }
}
