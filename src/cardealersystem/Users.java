package cardealersystem;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static List<User> users = new ArrayList<User>();

    public static void seedUsers() {
        for (int i = 0; i < 5; i++) {
            User temp = new User("admin" + i, "secret");
            users.add(temp);
        }
    }

    public static User findUser(String username) {
        for (User u :
                users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
