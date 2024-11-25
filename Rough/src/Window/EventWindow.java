package Window;

import EventManagement.DataManager;
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

            int terminalWidth = 150;
            int padding = (terminalWidth - "Select an Option (1-4): ".length()) / 2;
            for(int i = 0; i < padding; i++) {
                System.out.print(" ");
            }
            System.out.print("Select an Option (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Event ID: ");
                    String eventIDInput = scanner.nextLine();
                    while (!eventIDInput.matches("^[0-9]{6}$")) {
                        System.out.println("Invalid Event ID. Event ID must strictly be 6 digits long.");
                        eventIDInput = scanner.nextLine();
                    }
                    int eventID = Integer.parseInt(eventIDInput);

                    System.out.println("Enter Event Type: ");
                    String eventType = scanner.nextLine();
                    System.out.println("Enter Event Name: ");
                    String eventName = scanner.nextLine();
                    System.out.println("Enter Event Date: ");
                    String eventDate = scanner.nextLine();
                    eventManager.addNewEvent(eventID, eventType, eventName, eventDate);
                    break;
                case 2:
                    System.out.println("Enter Event ID: ");
                    String eventID2 = scanner.nextLine();
                    eventManager.removeEvent(eventID2);
                    break;
                case 3:
//                    DataManager dataManager = new DataManager();
//                    DataManager.EventDisplay();
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
