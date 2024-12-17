package Window;

import EventManagement.DataManager;
import EventManagement.EventManager;
import EventManagement.EventInputHandler;
import Global.GlobalMethod;

import java.util.Scanner;

import static Global.GlobalMethod.lineGaps;
import static Global.GlobalMethod.printCentered;

public class EventWindow implements Window {

    @Override
    public void showWindow() {
        lineGaps(8);

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
        EventInputHandler eventInputHandler = new EventInputHandler();

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
                    eventInputHandler.receiveEventInput();
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
