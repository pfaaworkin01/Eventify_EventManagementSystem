package Window;
import BudgetManagement.EventBudget;
import java.util.Scanner;

public class BudgetWindow implements Window{
    private EventBudget eventBudget;

    public BudgetWindow(String eventName, double totalBudget) {
        this.eventBudget = new EventBudget(eventName, totalBudget);
    }
}
