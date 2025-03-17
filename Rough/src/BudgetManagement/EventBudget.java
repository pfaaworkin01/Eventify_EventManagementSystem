package BudgetManagement;

public class EventBudget {
    private String eventName;
    private BudgetManager budgetManager;

    public EventBudget(String eventName) {
        this.eventName = eventName;
        this.budgetManager = new BudgetManager();
    }

    public String getEventName() {
        final String BLUE_TEXT = "\033[0;34m";
        final String RESET_TEXT = "\033[0m";


        return BLUE_TEXT + eventName+ RESET_TEXT;
    }

    public BudgetManager getBudgetManager() {
        return budgetManager;
    }

    public void displayEventSummary() {
        System.out.println("\nEvent Summary:");
       // System.out.println("Event Name: " + eventName);
        //System.out.println("Total Budget: $" + budgetManager.getTotalAllocatedBudget());
        budgetManager.viewEventBudgetSummary(eventName);
    }


}