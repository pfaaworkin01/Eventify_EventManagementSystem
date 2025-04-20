package BudgetManagement;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

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
        String leftAlignFormat = "| %-20s | %-15.2f | %-15.2f | %-15.2f |%n";
        System.out.format("+----------------------+-----------------+-----------------+-----------------+%n");
        System.out.format("| Department           | Allocated       | Spent           | Remaining       |%n");
        System.out.format("+----------------------+-----------------+-----------------+-----------------+%n");
        for (DepartmentBudget department : departmentBudgets) {
            System.out.format(leftAlignFormat, department.getDepartmentName(), department.getAllocatedBudget(), department.getTotalExpenses(), department.getRemainingBudget());
        }
        System.out.format("+----------------------+-----------------+-----------------+-----------------+%n");
    }


    public void viewEventBudgetSummary(String eventName) {
        final String BLUE_TEXT = "\033[0;34m";
        final String RESET_TEXT = "\033[0m";
        System.out.println(BLUE_TEXT + "Event Name: " + eventName + RESET_TEXT);
        System.out.println("\nTotal Event Budget: $" + getTotalAllocatedBudget());

        String leftAlignFormat = "| %-20s | %-15.2f | %-15.2f | %-15.2f |%n";
        System.out.format("+----------------------+-----------------+-----------------+-----------------+%n");
        System.out.format("| Department           | Allocated       | Spent           | Remaining       |%n");
        System.out.format("+----------------------+-----------------+-----------------+-----------------+%n");
        for (DepartmentBudget department : departmentBudgets) {
            System.out.format(leftAlignFormat, department.getDepartmentName(), department.getAllocatedBudget(), department.getTotalExpenses(), department.getRemainingBudget());
        }
        System.out.format("+----------------------+-----------------+-----------------+-----------------+%n");

        System.out.println("\nExpenses Details:");
        String expenseAlignFormat = "| %-20s | %-20s | %-10.2f | %-30s |%n";
        System.out.format("+----------------------+----------------------+------------+--------------------------------+%n");
        System.out.format("| Department           | Expense Name         | Amount     | Description                    |%n");
        System.out.format("+----------------------+----------------------+------------+--------------------------------+%n");
        for (DepartmentBudget department : departmentBudgets) {
            for (Expense expense : department.getExpenses()) {
                System.out.format(expenseAlignFormat, department.getDepartmentName(), expense.getExpenseName(), expense.getAmount(), expense.getDescription());
            }
        }
        System.out.format("+----------------------+----------------------+------------+--------------------------------+%n");
    }


    public List<DepartmentBudget> getDepartmentBudgets() {
        return departmentBudgets;
    }

    public void setDepartmentBudgets(List<DepartmentBudget> departmentBudgets) {
        this.departmentBudgets = departmentBudgets;
    }
}