package Window;

import EventManagement.EventDataManager;
import EventManagement.ParticipantEventRegistration;
import Global.GlobalData;
import Global.GlobalMethod;
import java.util.Scanner;

import static Global.GlobalData.LOGGED_IN_USERNAME;
import static Global.GlobalMethod.*;

public class ParticipantWindow implements Window {

    @Override
    public void showWindow() {
        printHeaderPart("Logged In as participant \"" + LOGGED_IN_USERNAME + "\"");
        System.out.println(" ".repeat(67) + "1. Sign Up for an Event");
        System.out.println(" ".repeat(67) + "2. Cancel Sign Up");
        System.out.println(" ".repeat(67) + "3. Log Out\n");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        EventDataManager eventDataManager = new EventDataManager();
        ParticipantEventRegistration participantEventRegistration = new ParticipantEventRegistration();
        while (!quit) {
            showWindow();

            for(int i = 0; i < 5; i++) {
                System.out.println();
            }

            GlobalMethod.insertPadding("Select an Option (1-3): ");
            System.out.print("Select an Option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    eventDataManager.displayEvents();
                    insertPadding("Select Event: ");
                    System.out.print("Select Event: ");
                    int eventID = scanner.nextInt();
                    scanner.nextLine();
                    participantEventRegistration.registerEvent(LOGGED_IN_USERNAME, eventID);
                    break;
                case 2:
                    eventDataManager.displayEvents();
                    break;
                case 3:
                    GlobalData.AUTHENTICATED = false;
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

}

