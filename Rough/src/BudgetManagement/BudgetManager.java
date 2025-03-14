package BudgetManagement;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetManager {
    private List<DepartmentBudget> departmentBudgets = new ArrayList<>();

    public double getTotalAllocatedBudget() {
        return departmentBudgets.stream().mapToDouble(DepartmentBudget::getAllocatedBudget).sum();
    }



    public void addDepartment(String departmentName, double allocatedBudget) {
        DepartmentBudget existingDepartment = findDepartment(departmentName);
        if (existingDepartment == null) {
            departmentBudgets.add(new DepartmentBudget(departmentName, allocatedBudget));
            System.out.println("Department added: " + departmentName + " with a budget of $" + allocatedBudget);
        } else {
            System.out.println("Department already exists: " + departmentName);
            System.out.print("Would you like to add the new amount to the previously allocated amount? (Y/N): ");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().trim().toUpperCase();
            if (response.equals("Y")) {
                existingDepartment.setAllocatedBudget(existingDepartment.getAllocatedBudget() + allocatedBudget);
                System.out.println("New amount added. Total allocated budget for " + departmentName + " is now $" + existingDepartment.getAllocatedBudget());
            } else {
                System.out.println("No changes made to the budget of " + departmentName);
            }
        }
    }
    public void addExpense(String departmentName, String expenseName, double amount, String description) {
        DepartmentBudget department = findDepartment(departmentName);
        if (department != null) {
            department.addExpense(expenseName, amount, description);
        } else {
            System.out.println("Error: Department not found.");
        }
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

    private DepartmentBudget findDepartment(String departmentName) {
        for (DepartmentBudget department : departmentBudgets) {
            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                return department;
            }
        }
        return null;
    }





}