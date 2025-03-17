package BudgetManagement;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import static Global.GlobalMethod.printCentered;

public class BudgetManager {

    private List<DepartmentBudget> departmentBudgets = new ArrayList<>();

    public double getTotalAllocatedBudget() {
        return departmentBudgets.stream().mapToDouble(DepartmentBudget::getAllocatedBudget).sum();
    }

    public void addDepartment(String departmentName, double allocatedBudget, Scanner scanner) {
        for (DepartmentBudget department : departmentBudgets) {
            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                System.out.println("Department already exists: " + departmentName);
                System.out.print("Do you want to add the new budget to the existing budget? (y/n): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("y")) {
                    department.setAllocatedBudget(department.getAllocatedBudget() + allocatedBudget);
                    System.out.println("New budget added. Total allocated budget for " + departmentName + " is now $" + department.getAllocatedBudget());
                } else {
                    System.out.println("No changes made to the existing budget.");
                }
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