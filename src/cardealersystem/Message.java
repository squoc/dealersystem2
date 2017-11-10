package cardealersystem;

import java.io.Serializable;

public class Message implements Serializable {

    public String content;

    public Message(String m) {
        this.content = m;
    }
}
