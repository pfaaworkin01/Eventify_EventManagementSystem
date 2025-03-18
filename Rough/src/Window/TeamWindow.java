package Window;

import EventManagement.EventDataManager;
import Global.GlobalData;
import TeamManagement.EventTeamMemberNames;
import TeamManagement.EventTeamsDataManager;

import java.util.ArrayList;
import java.util.Scanner;

import static Global.GlobalData.YELLOW_TEXT;
import static Global.GlobalMethod.*;

public class TeamWindow implements Window {
    @Override
    public void showWindow() {
        printHeaderPart("Managing Teams as \"" + GlobalData.LOGGED_IN_USERNAME + "\"");
        System.out.println(" ".repeat(64) + "1. Add Team Members");
        System.out.println(" ".repeat(64) + "2. View Team Members");
        System.out.println(" ".repeat(64) + "3. Remove Member(s)");
        System.out.println(" ".repeat(64) + "4. Create Custom Team Sector");
        System.out.println(" ".repeat(64) + "5. Go Back to Logged-In Menu\n");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while (!quit) {
            showWindow();
            int eventID;
            EventTeamsDataManager eventTeamsDataManager = new EventTeamsDataManager();

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
                    eventID = askToSelectEvent();
                    eventTeamsDataManager.createEventTeamDataFile(eventID);

                    if(EventDataManager.findEventByID(eventID)) {
                        ArrayList<String> teamMemberNames = EventTeamMemberNames.getNames();
                        String sectorName = eventTeamsDataManager.askToSelectEventSector(eventID);
                        eventTeamsDataManager.saveEventTeamData(eventID, teamMemberNames, sectorName);
                    }
                    else {
                        System.out.println();
                        printCentered("!!! Invalid Event ID !!!", YELLOW_TEXT);
                        System.out.println();
                        break;
                    }
                    break;
                case 2:
                    eventID = askToSelectEvent();
                    eventTeamsDataManager.createEventTeamDataFile(eventID);

                    if(EventDataManager.findEventByID(eventID)) {
                        eventTeamsDataManager.displayEventTeamData(eventID);
                    }
                    else {
                        System.out.println();
                        printCentered("!!! Invalid Event ID !!!", YELLOW_TEXT);
                        System.out.println();
                        break;
                    }
                    break;
                case 3:
                    eventID = askToSelectEvent();
                    eventTeamsDataManager.createEventTeamDataFile(eventID);

                    while (true) {
                        eventTeamsDataManager.displayEventTeamDataNoWait(eventID);
                        String sectorName = eventTeamsDataManager.askToSelectEventSector(eventID);
                        System.out.println();
                        insertPadding("Enter member name (enter Q/q to stop): ");
                        System.out.print("Enter member name (enter Q/q to stop): ");
                        String memberName = scanner.nextLine();
                        if (memberName.equals("Q") || memberName.equals("q")) {
                            System.out.println();
                            break;
                        }
                        eventTeamsDataManager.removeTeamMember(eventID, sectorName, memberName);
                    }
                    break;
                case 4:
                    eventID = askToSelectEvent();
                    insertPadding("Enter new sector name (enter Q/q to stop): ");
                    System.out.print("Enter new sector name (enter Q/q to stop): ");
                    String newSectorName = scanner.nextLine();
                    if (newSectorName.equals("Q") || newSectorName.equals("q")) {
                        System.out.println();
                        break;
                    }
                    eventTeamsDataManager.addCustomSector(eventID, newSectorName);
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

    private int askToSelectEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        EventDataManager.displayEventsShort();
        System.out.println();
        insertPadding("Select Event (Event ID): ");
        System.out.print("Select Event (Event ID): ");
        int eventID = scanner.nextInt();
        scanner.nextLine();

        return eventID;
    }
}

//public class TeamWindow implements Window {
//    @Override
//    public void showWindow() {
//        printHeaderPart("Managing Teams as \"" + GlobalData.LOGGED_IN_USERNAME + "\"");
//        System.out.println(" ".repeat(64) + "1. Add Members");
//        System.out.println(" ".repeat(64) + "2. View Team Members");
//        System.out.println(" ".repeat(64) + "3. Remove a Member");
//        System.out.println(" ".repeat(64) + "4. Create Custom Sector");
//        System.out.println(" ".repeat(64) + "5. Go Back to Logged-In Menu");
//        System.out.println(" ".repeat(64) + "6. Return to Main Menu\n");
//
//    }
//
//    @Override
//    public void askForInput() {
//        boolean quit = false;
//        Scanner scanner = new Scanner(System.in);
//        TeamManager teamManager = new TeamManager();
//
//        while (!quit) {
//            showWindow();
//
//            int terminalWidth = 154;
//            int padding = (terminalWidth - "Select an Option (1-6): ".length()) / 2;
//            for(int i = 0; i < padding; i++) {
//                System.out.print(" ");
//            }
//            System.out.print("Select an Option (1-6): ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    boolean back1 = false;
//                    while (!back1) {
//                        System.out.println();
//                        printCentered("Available Sectors:");
//                        teamManager.displaySectorNames();
//                        printCentered("Enter 0 to Quit\n");
//                        insertPadding("Select the sector number to add a member: ");
//                        System.out.print("Select the sector number to add a member: ");
//                        int sectorIndex = scanner.nextInt() - 1;
//                        scanner.nextLine();
//                        if (sectorIndex == -1) {
//                            back1 = true;
//                        }
//                        else if (!teamManager.choiceValidity(sectorIndex)) {
//                            System.out.println();
//                            printCentered("!!! Invalid Sector Number. Please choose a number from the table !!!", YELLOW_TEXT);
//                        }
//                        else {
//                            insertPadding("Enter the member's name: ");
//                            System.out.print("Enter the member's name: ");
//                            String memberName = scanner.nextLine();
//                            EventDataManager eventDataManager = new EventDataManager();
//                            eventDataManager.displayEvents();
//                            insertPadding("Select Event to add Member to (Event ID): ");
//                            System.out.print("Select Event to add Member to (Event ID): ");
//                            int eventID = scanner.nextInt();
//                            scanner.nextLine();
//                            teamManager.addMemberToSector(sectorIndex, memberName, eventID);
//                        }
//                    }
//                    break;
//                case 2:
//                    boolean back2 = false;
//                    while (!back2) {
//
//                        System.out.println();
//                        EventDataManager eventDataManager = new EventDataManager();
//                        eventDataManager.displayEvents();
//                        System.out.println();
//                        insertPadding("Select Event (Event ID). Enter 0 to Quit: ");
//                        System.out.print("Select Event (Event ID). Enter 0 to Quit: ");
//                        int eventID = scanner.nextInt();
//                        scanner.nextLine();
//
//                        if(TeamManager.findEventByID(eventID)) {
//                            back2 = true;
//                        }
//                        else if(eventID == 0) {
//
//                            back2 = true;
//                        }
//                        else {
//                            System.out.println();
//                            printCentered("!!! Event ID \"" + eventID + "\" not found. Try Again !!!", YELLOW_TEXT);
//                        }
//
////                        teamManager.displaySectorNames();
////                        printCentered("Enter 0 to Quit\n");
////                        insertPadding("Select which sector members to view: ");
////                        System.out.print("Select which sector members to view: ");
////                        int sectorIndex = scanner.nextInt() - 1;
////                        scanner.nextLine();
////                        if (sectorIndex == -1) {
////                            back2 = true;
////                        }
////                        else if (!teamManager.choiceValidity(sectorIndex)) {
////                            System.out.println();
////                            printCentered("!!! Invalid Sector Number. Please choose a number from the table !!!", YELLOW_TEXT);
////                        }
////                        else {
////                            teamManager.displaySectorMembers(sectorIndex, eventID);
////                            back2 = true;
////                        }
//                    }
//                    break;
//                case 3:
//                    System.out.println();
//                    EventDataManager eventDataManager = new EventDataManager();
//                    eventDataManager.displayEvents();
//                    insertPadding("Select Event (Event ID): ");
//                    System.out.print("Select Event (Event ID): ");
//                    int eventID = scanner.nextInt();
//                    scanner.nextLine();
//                    System.out.println("Available Sectors:");
//                    teamManager.displaySectorNames();
//                    System.out.println("0. Go Back");
//                    System.out.print("Select the sector to remove a member: ");
//                    int sectorIndex = scanner.nextInt() - 1;
//                    scanner.nextLine();
//                    if (sectorIndex != -1) {
//                        System.out.print("Enter the member's name to remove: ");
//                        String memberName = scanner.nextLine();
//                        teamManager.removeMemberFromSector(sectorIndex, memberName, eventID);
//                    }
//                    break;
//                case 4:
//                    System.out.println();
//                    EventDataManager eventDataManager2 = new EventDataManager();
//                    eventDataManager2.displayEvents();
//                    insertPadding("Select Event (Event ID): ");
//                    System.out.print("Select Event (Event ID): ");
//                    int eventID2 = scanner.nextInt();
//                    scanner.nextLine();
//                    insertPadding("Enter the name of the new sector: ");
//                    System.out.print("Enter the name of the new sector: ");
//                    String sectorName = scanner.nextLine();
//                    teamManager.addCustomSector(sectorName, eventID2);
//                    break;
//                case 5:
//                    quit = true;
//                    break;
//                case 6:
//                    quit = true;
//                    GlobalData.BACK_TO_MAIN_MENU = true;
//                    break;
//                default:
//                    System.out.println("Invalid Option");
//                    break;
//
//            }
//        }
//
//
//
//
//    }
//}
