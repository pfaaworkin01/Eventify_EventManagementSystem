package Window;

public class BudgetWindow implements Window{


    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        Window.printCentered("EVENTIFY");
        Window.printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        Window.printCentered("Budget Management\n");
        System.out.println(" ".repeat(65) + "1. Allocate Budget to a Department");
        System.out.println(" ".repeat(65) + "2. Add Expense to a Department");
        System.out.println(" ".repeat(65) + "3. View Department Budgets");
        System.out.println(" ".repeat(65) + "4. View Event Budget Summary");
        System.out.println(" ".repeat(65) + "5. Go Back");
    }
    
}
