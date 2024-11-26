package BudgetManagement;

public class Expense {
    private String expenseName;
    private double amount;
    private String description;

    public Expense(String expenseName, double amount, String description) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.description = description;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Expense: " + expenseName + ", Amount: " + amount + ", Description: " + description;
    }
}
