package Window;

import EventManagement.EventDataManager;
import Global.GlobalData;
import Team.TeamManager;

import java.util.Scanner;

import static Global.GlobalData.YELLOW_TEXT;
import static Global.GlobalMethod.*;

public class TeamWindow implements Window {
    @Override
    public void showWindow() {
        printHeaderPart("Managing Teams as \"" + GlobalData.LOGGED_IN_USERNAME + "\"");
        System.out.println(" ".repeat(64) + "1. Add Members");
        System.out.println(" ".repeat(64) + "2. View Team Members");
        System.out.println(" ".repeat(64) + "3. Remove a Member");
        System.out.println(" ".repeat(64) + "4. Create Custom Sector");
        System.out.println(" ".repeat(64) + "5. Go Back to Logged-In Menu");
        System.out.println(" ".repeat(64) + "6. Return to Main Menu\n");

    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        TeamManager teamManager = new TeamManager();

        while (!quit) {
            showWindow();

            int terminalWidth = 154;
            int padding = (terminalWidth - "Select an Option (1-6): ".length()) / 2;
            for(int i = 0; i < padding; i++) {
                System.out.print(" ");
            }
            System.out.print("Select an Option (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    boolean back1 = false;
                    while (!back1) {
                        System.out.println();
                        printCentered("Available Sectors:");
                        teamManager.displaySectorNames();
                        printCentered("Enter 0 to Quit\n");
                        insertPadding("Select the sector number to add a member: ");
                        System.out.print("Select the sector number to add a member: ");
                        int sectorIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (sectorIndex == -1) {
                            back1 = true;
                        }
                        else if (!teamManager.choiceValidity(sectorIndex)) {
                            System.out.println();
                            printCentered("!!! Invalid Sector Number. Please choose a number from the table !!!", YELLOW_TEXT);
                        }
                        else {
                            insertPadding("Enter the member's name: ");
                            System.out.print("Enter the member's name: ");
                            String memberName = scanner.nextLine();
                            EventDataManager eventDataManager = new EventDataManager();
                            eventDataManager.displayEvents();
                            insertPadding("Select Event to add Member to (Event ID): ");
                            System.out.print("Select Event to add Member to (Event ID): ");
                            int eventID = scanner.nextInt();
                            scanner.nextLine();
                            teamManager.addMemberToSector(sectorIndex, memberName, eventID);
                        }
                    }
                    break;
                case 2:
                    boolean back2 = false;
                    while (!back2) {
                        System.out.println();
                        EventDataManager eventDataManager = new EventDataManager();
                        eventDataManager.displayEvents();
                        insertPadding("Select Event (Event ID): ");
                        System.out.print("Select Event (Event ID): ");
                        int eventID = scanner.nextInt();
                        scanner.nextLine();
                        teamManager.displaySectorNames();
                        printCentered("Enter 0 to Quit\n");
                        insertPadding("Select which sector members to view: ");
                        System.out.print("Select which sector members to view: ");
                        int sectorIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (sectorIndex == -1) {
                            back2 = true;
                        }
                        else if (!teamManager.choiceValidity(sectorIndex)) {
                            System.out.println();
                            printCentered("!!! Invalid Sector Number. Please choose a number from the table !!!", YELLOW_TEXT);
                        }
                        else {
                            teamManager.displaySectorMembers(sectorIndex, eventID);
                            back2 = true;
                        }
                    }
                    break;
                case 3:
                    System.out.println();
                    EventDataManager eventDataManager = new EventDataManager();
                    eventDataManager.displayEvents();
                    insertPadding("Select Event (Event ID): ");
                    System.out.print("Select Event (Event ID): ");
                    int eventID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Available Sectors:");
                    teamManager.displaySectorNames();
                    System.out.println("0. Go Back");
                    System.out.print("Select the sector to remove a member: ");
                    int sectorIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (sectorIndex != -1) {
                        System.out.print("Enter the member's name to remove: ");
                        String memberName = scanner.nextLine();
                        teamManager.removeMemberFromSector(sectorIndex, memberName, eventID);
                    }
                    break;
                case 4:
                    System.out.println();
                    EventDataManager eventDataManager2 = new EventDataManager();
                    eventDataManager2.displayEvents();
                    insertPadding("Select Event (Event ID): ");
                    System.out.print("Select Event (Event ID): ");
                    int eventID2 = scanner.nextInt();
                    scanner.nextLine();
                    insertPadding("Enter the name of the new sector: ");
                    System.out.print("Enter the name of the new sector: ");
                    String sectorName = scanner.nextLine();
                    teamManager.addCustomSector(sectorName, eventID2);
                    break;
                case 5:
                    quit = true;
                    break;
                case 6:
                    quit = true;
                    GlobalData.BACK_TO_MAIN_MENU = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;

            }
        }




    }
}
