package BudgetManagement;

import java.util.ArrayList;
import java.util.List;

public class DepartmentBudget {
    private String departmentName;
    private double allocatedBudget;
    private List<Expense> expenses;

    public DepartmentBudget(String departmentName, double allocatedBudget) {
        this.departmentName = departmentName;
        this.allocatedBudget = allocatedBudget;
        this.expenses = new ArrayList<>();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public double getAllocatedBudget() {
        return allocatedBudget;
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public double getRemainingBudget() {
        return allocatedBudget - getTotalExpenses();
    }

    public void addExpense(String expenseName, double amount, String description) {
        if (getTotalExpenses() + amount > allocatedBudget) {
            System.out.println("Error: Expense exceeds the allocated budget for " + departmentName);
        } else {
            expenses.add(new Expense(expenseName, amount, description));
            System.out.println("Expense added to " + departmentName + ": " + expenseName + " (" + amount + ")");
        }
    }

    public void displayExpenses() {
        System.out.println("\nExpenses for Department: " + departmentName);
        System.out.println("Allocated Budget: " + allocatedBudget);
        System.out.println("Total Expenses: " + getTotalExpenses());
        System.out.println("Remaining Budget: " + getRemainingBudget());
        System.out.println("Details:");
        for (Expense expense : expenses) {
            System.out.println("  - " + expense);
        }
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
