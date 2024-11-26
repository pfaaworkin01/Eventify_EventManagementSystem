package BudgetManagement;

import java.util.ArrayList;
import java.util.List;

public class EventBudget {
    private String eventName;
    private double totalBudget;
    private List<DepartmentBudget> departments;

    public EventBudget(String eventName, double totalBudget) {
        this.eventName = eventName;
        this.totalBudget = totalBudget;
        this.departments = new ArrayList<>();
    }
    public void addDepartment(String departmentName, double allocatedBudget) {
        if (getTotalAllocatedBudget() + allocatedBudget > totalBudget) {
            System.out.println("Error: Total department budgets exceed the event budget.");
        } else {
            departments.add(new DepartmentBudget(departmentName, allocatedBudget));
            System.out.println("Department added: " + departmentName + " (Budget: " + allocatedBudget + ")");
        }
    }
}
