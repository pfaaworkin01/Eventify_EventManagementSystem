package BudgetManagement;

public class EventBudget {
    private String eventName;
    private BudgetManager budgetManager;

    public EventBudget(String eventName) {
        this.eventName = eventName;
        this.budgetManager = new BudgetManager();
    }

    public String getEventName() {
        return eventName;
    }

    public BudgetManager getBudgetManager() {
        return budgetManager;
    }

    public void displayEventSummary() {
        System.out.println("\nEvent Summary:");
        System.out.println("Event Name: " + eventName);
        System.out.println("Total Budget: $" + budgetManager.getTotalAllocatedBudget());
        budgetManager.viewEventBudgetSummary();
    }
}