package cardealersystem;

public class Employee extends PersonBase {
    public int employeeID;
    public String department;
    public String email;
    public boolean hourlyEmployee = false;
    public double salary = 0.0;
    private int ssn;

    public Employee(String first, String last, String add, int id, String department, String email, int ssn, double salary) {
        super(first, last, add);
        this.employeeID = id;
        this.department = department;
        this.email = email;
        this.ssn = ssn;
        this.salary = salary;
    }

    public int getSSN() {
        return this.ssn;
    }

    public String getInfo() {
        String baseInfo = super.getInfo();
        baseInfo += "\nEmployeeID: " + this.employeeID + "\nDepartment: " + this.department
                        + "\nEmail: " + this.email;
        return baseInfo;
    }
}
