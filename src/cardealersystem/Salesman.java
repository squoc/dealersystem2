package cardealersystem;

public class Salesman extends Employee {
    private int numberOfSales;
    public Employee supervisor;

    public Salesman(String first, String last, String add, int id, String department, String email, int ssn, double salary) {
        super(first, last, add, id, department, email, ssn, salary);
    }

    public void setNumberOfSales(int num) {
        this.numberOfSales = num;
    }

    public int getNumberOfSales() {
        return this.numberOfSales;
    }
}
