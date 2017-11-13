package cardealersystem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public List<Part> parts = new ArrayList<Part>();
    public List<Car> cars = new ArrayList<Car>();

    public void addCar(Car c) {
        this.cars.add(c);
    }

    public Car searchForCar(String vin) {
        for (Car c :
                cars) {
            if (c.VIN.equals(vin))
                return c;
        }
        return null;
    }

    public void addPart(Part p) {
        this.parts.add(p);
    }

    public Part searchForPart(int id) {
        for (Part p:
                parts) {
            if (p.partID == id)
                return p;
        }
        return null;
    }
}
