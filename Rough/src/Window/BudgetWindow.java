package Window;

import  BudgetManagement.BudgetFileManager;
import BudgetManagement.DepartmentBudget;
import BudgetManagement.EventBudget;
import EventManagement.EventManager;
import EventManagement.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static Global.GlobalMethod.printCentered;
import static Global.GlobalMethod.printHeaderPart;

public class BudgetWindow implements Window {
    private Map<Integer, EventBudget> eventBudgets; // Keyed by event ID
    private EventManager eventManager;

    public BudgetWindow() {
        this.eventManager = new EventManager();
        this.eventBudgets = new HashMap<>();
        loadBudgetInfo();
    }

    @Override
    public void showWindow() {
        printHeaderPart("Budget Management");
        System.out.println(" ".repeat(67) + "1. Select an Event");
        System.out.println(" ".repeat(67) + "2. View All Events");
        System.out.println(" ".repeat(67) + "3. Go Back");
    }

    @Override
    public void askForInput() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            showWindow();

            System.out.print("Select an Option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

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
        System.out.print("\nEnter the event ID: ");
        int eventID = scanner.nextInt();
        scanner.nextLine();

        Event event = eventManager.getEventByID(eventID);
        if (event != null) {
            if (!eventBudgets.containsKey(eventID)) {
                // If no budget exists for this event, create a new one
                EventBudget newBudget = new EventBudget(event.getEventName());
                eventBudgets.put(eventID, newBudget);
            }
            manageEventBudget(scanner, eventID);
        } else {
            System.out.println("Error: Event not found. Please try again.");
        }
    }

    private void viewAllEvents() {
        eventManager.displayAllEvents();
    }

    private void manageEventBudget(Scanner scanner, int eventID) {
        EventBudget eventBudget = eventBudgets.get(eventID);
        boolean quit = false;

        while (!quit) {
            System.out.println("\nManaging Budget for Event ID: " + eventID);
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
                    allocateBudgetToDepartment(scanner, eventBudget);
                    break;
                case 2:
                    addExpenseToDepartment(scanner, eventBudget);
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

    private void allocateBudgetToDepartment(Scanner scanner, EventBudget eventBudget) {
        List<DepartmentBudget> departments = eventBudget.getBudgetManager().getDepartmentBudgets();
        if (departments.isEmpty()) {
            System.out.println("No departments available. Please add a department first.");
            return;
        }

        System.out.println("Available Departments:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getDepartmentName());
        }
        System.out.print("Select the department number: ");
        int departmentIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (departmentIndex >= 0 && departmentIndex < departments.size()) {
            System.out.print("Enter budget amount: ");
            double budget = scanner.nextDouble();
            scanner.nextLine();
            eventBudget.getBudgetManager().addDepartment(departments.get(departmentIndex).getDepartmentName(), budget);
            BudgetFileManager.saveBudgetInfo(eventBudget.getBudgetManager().getDepartmentBudgets());
        } else {
            System.out.println("Invalid department number.");
        }
    }
    private void loadBudgetInfo() {
        for (Event event : eventManager.getAllEvents()) {
            EventBudget eventBudget = new EventBudget(event.getEventName());
            eventBudget.getBudgetManager().setDepartmentBudgets(BudgetFileManager.loadBudgetInfo());
            eventBudgets.put(event.getEventID(), eventBudget);
        }
    }

    private void addExpenseToDepartment(Scanner scanner, EventBudget eventBudget) {
        List<DepartmentBudget> departments = eventBudget.getBudgetManager().getDepartmentBudgets();
        if (departments.isEmpty()) {
            System.out.println("No departments available. Please add a department first.");
            return;
        }

        System.out.println("Available Departments:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getDepartmentName());
        }
        System.out.print("Select the department number: ");
        int departmentIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (departmentIndex >= 0 && departmentIndex < departments.size()) {
            System.out.print("Enter expense name: ");
            String expenseName = scanner.nextLine();
            System.out.print("Enter expense amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter expense description: ");
            String description = scanner.nextLine();
            eventBudget.getBudgetManager().addExpense(departments.get(departmentIndex).getDepartmentName(), expenseName, amount, description);
            BudgetFileManager.saveBudgetInfo(eventBudget.getBudgetManager().getDepartmentBudgets());
        } else {
            System.out.println("Invalid department number.");
        }
    }
}