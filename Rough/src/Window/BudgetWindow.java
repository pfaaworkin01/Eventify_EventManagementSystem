package Window;
import BudgetManagement.EventBudget;
import java.util.Scanner;
import java.util.HashMap;

public class BudgetWindow implements Window {
    private HashMap<String, EventBudget> eventBudgets;


    public BudgetWindow() {
        this.eventBudgets = new HashMap<>();
    }

    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        Window.printCentered("EVENTIFY");
        Window.printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        Window.printCentered("Budget Management\n");
        System.out.println(" ".repeat(65) + "1. Select an Event");
        System.out.println(" ".repeat(65) + "2. View All Events");
        System.out.println(" ".repeat(65) + "3. Go Back");
    }

    @Override
    public void askForInput() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            showWindow();

            System.out.print("Select an Option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    selectEvent(scanner);
                    break;
                case 2:
                    viewAllEvents();
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
    private void selectEvent(Scanner scanner) {
        System.out.print("\nEnter the event name: ");
        String eventName = scanner.nextLine();

        EventBudget selectedEvent = eventBudgets.get(eventName);
        if (selectedEvent != null) {
            manageEventBudget(scanner, selectedEvent);
        } else {
            System.out.println("Error: Event not found. Please try again.");
        }
    }
    private void viewAllEvents() {
        System.out.println("\nAvailable Events:");
        for (String eventName : eventBudgets.keySet()) {
            System.out.println("  - " + eventName);
        }
    }
    private void manageEventBudget(Scanner scanner, EventBudget eventBudget) {
        boolean quit = false;

        while (!quit) {
            System.out.println("\nManaging Budget for: " + eventBudget.getEventName());
            System.out.println(" ".repeat(65) + "1. Allocate Budget to a Department");
            System.out.println(" ".repeat(65) + "2. Add Expense to a Department");
            System.out.println(" ".repeat(65) + "3. View Department Budgets");
            System.out.println(" ".repeat(65) + "4. View Event Budget Summary");
            System.out.println(" ".repeat(65) + "5. Go Back");

            System.out.print("Select an Option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter department name: ");
                    String departmentName = scanner.nextLine();
                    System.out.print("Enter budget amount: ");
                    double budget = scanner.nextDouble();
                    scanner.nextLine();
                    eventBudget.getBudgetManager().addDepartment(departmentName, budget);
                    break;
                case 2:
                    System.out.print("Enter department name: ");
                    String deptName = scanner.nextLine();
                    System.out.print("Enter expense name: ");
                    String expenseName = scanner.nextLine();
                    System.out.print("Enter expense amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter expense description: ");
                    String description = scanner.nextLine();
                    eventBudget.getBudgetManager().addExpense(deptName, expenseName, amount, description);
                    break;
                case 3:
                    eventBudget.getBudgetManager().viewDepartmentBudgets();
                    break;
                case 4:
                    eventBudget.displayEventSummary();
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }





}
