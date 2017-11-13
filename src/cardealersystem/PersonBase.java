package cardealersystem;

public abstract class PersonBase {
    public String firstName;
    public String lastName;
    public String address;

    public PersonBase() {
        this.firstName = "NA";
        this.lastName = "NA";
        this.address = "NA";
    }

    public PersonBase(String first, String last, String add) {
        this.firstName = first;
        this.lastName = last;
        this.address = add;
    }

    public String getInfo() {
        String baseInfo = "Name: " + this.firstName + " " + this.lastName + "\nAddress: " + this.address;
        return baseInfo;
    }
}
