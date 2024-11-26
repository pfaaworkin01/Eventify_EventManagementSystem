package BudgetManagement;

import java.util.List;
import java.util.ArrayList;

public class BudgetManager {   private List<DepartmentBudget> departmentBudgets = new ArrayList<>();
    private double totalEventBudget;

    public BudgetManager(double totalEventBudget) {
        this.totalEventBudget = totalEventBudget;
    }

    public double getTotalEventBudget() {
        return totalEventBudget;
    }

    public double getTotalAllocatedBudget() {
        return departmentBudgets.stream().mapToDouble(DepartmentBudget::getRemainingBudget).sum();
    }

    public void addDepartment(String departmentName, double allocatedBudget) {
        double totalAllocated = getTotalAllocatedBudget();

        if (totalAllocated + allocatedBudget > totalEventBudget) {
            System.out.println("Error: Total allocated budget exceeds event budget.");
        } else {
            departmentBudgets.add(new DepartmentBudget(departmentName, allocatedBudget));
            System.out.println("Department added: " + departmentName + " with a budget of $" + allocatedBudget);
        }
    }

    public void addExpense(String departmentName, String expenseName, double amount, String description) {
        for (DepartmentBudget department : departmentBudgets) {
            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                department.addExpense(expenseName, amount, description);
                return;
            }
        }
        System.out.println("Error: Department not found.");
    }

    public void viewDepartmentBudgets() {
        departmentBudgets.forEach(DepartmentBudget::displayExpenses);
    }

    public void viewEventBudgetSummary() {
        System.out.printf("%-20s %-15s %-15s %-15s%n", "Department", "Allocated", "Spent", "Remaining");
        departmentBudgets.forEach(DepartmentBudget::displayExpenses);
        System.out.println("\nTotal Event Budget: $" + totalEventBudget);
    }
}
