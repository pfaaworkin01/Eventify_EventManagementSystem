package BudgetManagement;

import java.util.ArrayList;
import java.util.List;

public class EventBudget {
    private String eventName;
    private double totalBudget;
    private List<DepartmentBudget> departments;

    public EventBudget(String eventName, double totalBudget) {
        this.eventName = eventName;
        this.totalBudget = totalBudget;
        this.departments = new ArrayList<>();
    }
}
