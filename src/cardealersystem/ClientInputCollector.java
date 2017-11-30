package cardealersystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientInputCollector {

    public static String getInput() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            return in.readLine();
        } catch (Exception e) {
            return "";
        }
    }
}
