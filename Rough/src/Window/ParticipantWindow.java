package Window;

import EventManagement.DataManager;
import Global.GlobalData;
import Global.GlobalMethod;
import java.util.Scanner;

import static Global.GlobalMethod.*;

public class ParticipantWindow implements Window {

    @Override
    public void showWindow() {
        printHeaderPart("Logged In as participant \"" + GlobalData.LOGGED_IN_USERNAME + "\"");
        System.out.println(" ".repeat(67) + "1. Sign Up for an Event");
        System.out.println(" ".repeat(67) + "2. Cancel Sign Up");
        System.out.println(" ".repeat(67) + "3. Log Out\n");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        DataManager dataManager = new DataManager();

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
                    dataManager.displayEvents();
                    break;
                case 2:
                    dataManager.displayEvents();
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

