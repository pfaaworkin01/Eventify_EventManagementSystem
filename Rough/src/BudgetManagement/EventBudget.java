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
    public double getTotalAllocatedBudget() {
        double total = 0;
        for (DepartmentBudget department : departments) {
            total += department.getAllocatedBudget();
        }
        return total;
    }
    public void addExpenseToDepartment(String departmentName, String expenseName, double amount, String description) {
        for (DepartmentBudget department : departments) {
            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                department.addExpense(expenseName, amount, description);
                return;
            }
        }
        System.out.println("Error: Department not found.");
    }
    public void displayExpenseSummary() {
        System.out.println("\nExpense Summary for Event: " + eventName);
        System.out.printf("%-20s %-15s %-15s %-15s%n", "Department", "Allocated", "Spent", "Remaining");
        for (DepartmentBudget department : departments) {
            System.out.printf("%-20s %-15.2f %-15.2f %-15.2f%n",
                    department.getDepartmentName(),
                    department.getAllocatedBudget(),
                    department.getTotalExpenses(),
                    department.getRemainingBudget());
        }
        System.out.println("Total Event Budget: " + totalBudget);
        System.out.println("Total Allocated: " + getTotalAllocatedBudget());
    }
}
