package cardealersystem;

public class RequestHandler {

    public String request;

    public RequestHandler(String request) {
        this.request = request;
    }


//                if (request.equals("car inventory check")) {
//        // get car inventory
//        response = "query some car inventory.";
//    }
//                else if (request.equals(("part inventory check"))) {
//        // get part inventory
//        response = "query some part";
//    }

    public String produceResponse() {
        return "response from server";
    }
}
