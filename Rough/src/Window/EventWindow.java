package Window;

import EventManagement.DataManager;
import EventManagement.EventManager;
import EventManagement.EventTypeTable;
import EventManagement.InputValidator;
import Global.GlobalMethod;

import java.io.IOException;
import java.util.Scanner;

import static Global.GlobalMethod.clearConsole;
import static Global.GlobalMethod.printCentered;

public class EventWindow implements Window {

    @Override
    public void showWindow() {
        clearConsole();

        System.out.println();
        System.out.println("*".repeat(154));
        printCentered("EVENTIFY");
        printCentered("Make Every Event Count");
        System.out.println("*".repeat(154));
        printCentered("<<< Managing Events >>>\n");
        System.out.println(" ".repeat(67) + "1. Add New Event");
        System.out.println(" ".repeat(67) + "2. Cancel an Event");
        System.out.println(" ".repeat(67) + "3. Display Upcoming Events");
        System.out.println(" ".repeat(67) + "4. Back to Logged In Menu");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        EventManager eventManager = new EventManager();
        InputValidator inputValidator = new InputValidator();

        while (!quit) {
            showWindow();

            for(int i = 0; i < 5; i++) {
                System.out.println("\n");
            }

            GlobalMethod.insertPadding("Select an Option (1-4): ");
            System.out.print("Select an Option (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    GlobalMethod.insertPadding("Enter Event ID: ");
                    System.out.printf("Enter Event ID: ");
                    int eventID = scanner.nextInt();
                    scanner.nextLine();
                    while(!inputValidator.eventIDValid(eventID)) {
                        GlobalMethod.insertPadding("Enter Event ID: ");
                        System.out.printf("Enter Event ID: ");
                        eventID = scanner.nextInt();
                        scanner.nextLine();
                    }

                    EventTypeTable eventTypeTable = new EventTypeTable();
                    String eventType = eventTypeTable.selectEventType();

                    GlobalMethod.insertPadding("Enter Event Name: ");
                    System.out.printf("Enter Event Name: ");
                    String eventName = scanner.nextLine();

                    GlobalMethod.insertPadding("Enter Event Date: ");
                    System.out.printf("Enter Event Date: ");
                    String eventDate = scanner.nextLine();

                    eventManager.addNewEvent(eventID, eventType, eventName, eventDate);
                    break;
                case 2:
                    System.out.println("Enter Event ID: ");
                    String eventID2 = scanner.nextLine();
                    eventManager.removeEvent(eventID2);
                    break;
                case 3:
                    DataManager dataManager = new DataManager();
                    dataManager.displayEvents();
                    String doneViewingEvents = "N";
                    while (!doneViewingEvents.equalsIgnoreCase("Y")) {
                        GlobalMethod.insertPadding("Quit? (Y/N): ");
                        System.out.print("Quit? (Y/N): ");
                        doneViewingEvents = scanner.nextLine();
                    }
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

}
