package Window;

import EventManagement.EventManager;

import java.util.Scanner;

public class EventWindow implements Window {

    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        Window.printCentered("EVENTIFY");
        Window.printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        Window.printCentered("Managing Events\n");
        System.out.println(" ".repeat(65) + "1. Add New Event");
        System.out.println(" ".repeat(65) + "2. Cancel an Event");
        System.out.println(" ".repeat(65) + "3. Display Upcoming Events");
        System.out.println(" ".repeat(65) + "4. Back to Logged In Menu");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        EventManager eventManager = new EventManager();

        while (!quit) {
            showWindow();
            System.out.println("Select an Option (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Event ID: ");
                    int eventID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Event Name: ");
                    String eventName = scanner.nextLine();
                    System.out.println("Enter Event Date: ");
                    String eventDate = scanner.nextLine();
                    eventManager.addNewEvent(eventID, eventName, eventDate);
                    break;
                case 2:
                    System.out.println("Enter Event ID: ");
                    eventID = scanner.nextInt();
                    eventManager.removeEvent(eventID);
                    break;
                case 3:
                    eventManager.displayEvents();
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
