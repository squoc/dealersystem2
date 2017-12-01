package cardealersystem;


// this class is the handler for action code sent from client and produce corresponding output

import java.util.Arrays;
import java.util.List;

public class RequestHandler {

    public String request;
    private String actionCode;
    private String data;
    private String response;


    public RequestHandler(String request) {
        this.request = request;
        extractData();
    }


    private void extractData() {

        // extract action code from client
        String[] info = request.split(";");
        actionCode = info[0];

        // if the code is 102, 103... then it has data attached
        List listOfCodes = Arrays.asList("102", "103", "104", "202", "203");

        // extract data attached with the action code
        if (listOfCodes.contains(actionCode)) {
            data = info[1];
        }
    }

    private void processData() {

        // switch code to get appropriate action
        switch (actionCode) {

            // get all cars in inventory
            case "101":
                String size = "There are totally " + Inventory.cars.size() + " cars in inventory\n";
                response = size + Inventory.serializeCarList(Inventory.cars);
                break;

            // find cars by make
            case "102":
                List<Car> carList = Inventory.findCarsByMake(data);

                if (!carList.isEmpty())
                    response = Inventory.serializeCarList(carList);
                else
                    response = "Not found.";
                break;

            // find car by VIN
            case "103":
                Car car = Inventory.findCarByVIN(data);
                if (car != null)
                    response = car.getCarInfo();
                else
                    response = "Not found.";
                break;

            // add new car from data client supplies
            case "104":
                // process data submitted by client
                String[] details = data.split(",");
                String vin = details[0];
                String make = details[1];
                String color = details[2];
                int doors = Integer.parseInt(details[3]);
                int seats = Integer.parseInt(details[4]);
                double price = Double.parseDouble(details[5]);
                int mpg = Integer.parseInt(details[6]);
                int power = Integer.parseInt(details[7]);

                Car newCar = new Car(vin, make, color, doors, seats, price, mpg, power);
                Inventory.addCar(newCar);

                response = "New car added.";
                break;

            // get all parts in inventory
            case "201":
                // serialize list of part
                String partSize = "There are totally " + Inventory.parts.size() + " parts in inventory\n";
                response = partSize + Inventory.serializePartList(Inventory.parts);
                break;

            // find part by ID
            case "202":
                int id = Integer.parseInt(data);
                Part p = Inventory.findPartById(id);
                if (p != null)
                    response = p.getPartInfo();
                else
                    response = "Not found.";
                break;

            // add new part to inventory
            case "203":
                String[] partDetails = data.split(",");
                int partID = Integer.parseInt(partDetails[0]);
                String des = partDetails[1];
                double partPrice = Double.parseDouble(partDetails[2]);
                String origin = partDetails[3];

                Part newPart = new Part(partID, des, partPrice, origin);
                Inventory.addPart(newPart);
                response = "New part added.";
                break;

            // get all sales
            case "301":
                String totalSaleOrders = "There are totally " + SeedPurchases.purchases.size() + " sale orders.\n";
                String cost = "Total sale value: " + SeedPurchases.getPurchasesCost() + " dollars\n";

                response = totalSaleOrders + cost;
                break;

            default:
                response = "Invalid request";
        }
    }


    // produce corresponding response for each action code
    public String produceResponse() {
        processData();
        return response;
    }
}
