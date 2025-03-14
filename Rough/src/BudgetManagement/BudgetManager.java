package BudgetManagement;

import java.util.List;
import java.util.ArrayList;

public class BudgetManager {
    private List<DepartmentBudget> departmentBudgets = new ArrayList<>();

    public double getTotalAllocatedBudget() {
        return departmentBudgets.stream().mapToDouble(DepartmentBudget::getAllocatedBudget).sum();
    }

    public void addDepartment(String departmentName, double allocatedBudget) {
        for (DepartmentBudget department : departmentBudgets) {
            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                System.out.println("Department already exists: " + departmentName);
                return;
            }
        }
        departmentBudgets.add(new DepartmentBudget(departmentName, allocatedBudget));
        System.out.println("Department added: " + departmentName + " with a budget of $" + allocatedBudget);
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
        System.out.println("\nTotal Event Budget: $" + getTotalAllocatedBudget());
    }
    public List<DepartmentBudget> getDepartmentBudgets() {
        return departmentBudgets;
    }

    public void setDepartmentBudgets(List<DepartmentBudget> departmentBudgets) {
        this.departmentBudgets = departmentBudgets;
    }

}