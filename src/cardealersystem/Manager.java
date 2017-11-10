package cardealersystem;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    public List<Employee> teamMembers = new ArrayList<Employee>();

    public Manager(String first, String last, String add, int id, String department, String email, int ssn, double salary) {
        super(first, last, add, id, department, email, ssn, salary);
    }

    public void addTeamMeber(Employee e) {
        this.teamMembers.add(e);
    }

    public void removeTeamMember(Employee e) {
        this.teamMembers.remove(e);
    }

    public Employee searchTeamMember(String name) {
        for (Employee e :
                teamMembers) {
            if (e.firstName.equals(name) || e.lastName.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
