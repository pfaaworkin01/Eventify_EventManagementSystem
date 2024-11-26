package BudgetManagement;

import java.util.ArrayList;
import java.util.List;

public class EventBudget {
    private String eventName;
    private double totalBudget;
    private BudgetManager budgetManager;

    public EventBudget(String eventName, double totalBudget) {
        this.eventName = eventName;
        this.totalBudget = totalBudget;
        this.budgetManager = new BudgetManager(totalBudget);
    }

    public String getEventName() {
        return eventName;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public BudgetManager getBudgetManager() {
        return budgetManager;
    }

    public void displayEventSummary() {
        System.out.println("\nEvent Summary:");
        System.out.println("Event Name: " + eventName);
        System.out.println("Total Budget: $" + totalBudget);
        budgetManager.viewEventBudgetSummary();
    }
}
