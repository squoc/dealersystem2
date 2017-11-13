package cardealersystem;

public class Car extends VehicleBase {
    public int numberOfDoors;
    public int numberOfSeats;

    public Car(String vin, String make, String color, int numberOfDoors, int numberOfSeats, double retailPrice, int mpg, int horsePower) {
        super(vin, color, make, retailPrice, mpg, horsePower);
        this.numberOfDoors = numberOfDoors;
        this.numberOfSeats = numberOfSeats;
    }

    public String getCarInfo() {
        String baseInfo = super.getVehicleInfo();
        baseInfo += "\nNumber of Seats: " + this.numberOfSeats + "\nNumber of Doors: " + this.numberOfDoors;

        return baseInfo;
    }

}
