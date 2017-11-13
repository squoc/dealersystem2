package cardealersystem;

public class Motorcycle extends VehicleBase {

    public int engineSize;
    public double weight;

    public Motorcycle(String vin, String make, String color, double retailPrice, int mpg, int horsePower, int size, double weight) {
        super(vin, color, make, retailPrice, mpg, horsePower);
        this.engineSize = size;
        this.weight = weight;
    }

    public String getMotocycleInfo() {
        String baseInfo = super.getVehicleInfo();
        baseInfo += "\nEngine size: " + this.engineSize + "\nWeight: " + this.weight;

        return baseInfo;
    }
}
