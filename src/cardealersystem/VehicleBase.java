package cardealersystem;

public abstract class VehicleBase {
    public String VIN;
    public String color;
    public String make;
    public double retailPrice;
    public int mpg;
    public int horsePower;

    public VehicleBase(String vin, String make, String color, double retailPrice, int mpg, int horsePower) {
        this.VIN = vin;
        this.make = make;
        this.color = color;
        this.retailPrice = retailPrice;
        this.horsePower = horsePower;
        this.mpg = mpg;
    }

    public String getVehicleInfo() {
        String info = "VIN #: " + this.VIN + "\nColor: " + this.color + "\nMake: " + this.make
                        + "\nRetail Price: " + this.retailPrice + "\nMileage: " + this.mpg
                        + "\nHorse Power: " + this.horsePower;

        return info;
    }
}
