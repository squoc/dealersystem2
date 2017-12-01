package cardealersystem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public static List<Part> parts = new ArrayList<Part>();
    public static List<Car> cars = new ArrayList<Car>();

    public static void seedParts() {
        Part p1 = new Part(1, "Air Filter", 25.0, "China");
        parts.add(p1);

        Part p2 = new Part(2, "Brake Rotor", 217, "US");
        parts.add(p2);

        Part p3 = new Part(3, "Radiator Hose", 20, "China");
        parts.add(p3);

        Part p4 = new Part(4, "Coolant", 12, "US");
        parts.add(p4);

        Part p5 = new Part(5, "Battery", 120, "US");
        parts.add(p5);
    }

    public static void seedCars() {
        Car c1 = new Car("111111Z", "Honda", "White", 4, 4,25000, 60, 500);
        cars.add(c1);

        Car c2 = new Car("222222Z", "Toyota", "Matte Black", 4,4,30000, 60, 600);
        cars.add(c2);

        Car c3 = new Car("333333Z", "Infinity", "Yellow", 2, 4, 43000, 72, 500);
        cars.add(c3);

        Car c4 = new Car("444444Z", "Acura", "Green", 4, 4,34000, 72, 800);
        cars.add(c4);

        Car c5 = new Car("555555Z", "Madza", "Red", 2,2,20000, 60, 500);
        cars.add(c5);
    }

    public static String serializePartList(List<Part> partList) {
        String result = "";
        for (Part p :
                partList) {
            result += p.getPartInfo();
        }

        return result;
    }

    public static String serializeCarList(List<Car> carList) {
        String result = "";
        for (Car c :
                carList) {
            result += c.getCarInfo();
        }

        return result;
    }

    public static void addCar(Car c) {
        cars.add(c);
    }

    public static Car findCarByVIN(String vin) {
        String v = vin.toUpperCase();

        for (Car c :
                cars) {
            if (c.VIN.toUpperCase().equals(v))
                return c;
        }

        return null;
    }
    
    public static List<Car> findCarsByMake(String make) {
        String m = make.toUpperCase();

        List<Car> carsByMake = new ArrayList<Car>();

        for (Car c :
                cars) {
            if (c.make.toUpperCase().equals(m))
                carsByMake.add(c);
        }

        return carsByMake;
    }

    public static void addPart(Part p) {
        parts.add(p);
    }

    public static Part findPartById(int id) {
        for (Part p:
                parts) {
            if (p.partID == id)
                return p;
        }

        return null;
    }

}
