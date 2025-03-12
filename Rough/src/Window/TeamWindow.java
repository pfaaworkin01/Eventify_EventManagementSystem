package Window;

import Global.GlobalData;
import Team.TeamManager;

import java.util.Scanner;

import static Global.GlobalData.YELLOW_TEXT;
import static Global.GlobalMethod.*;

public class TeamWindow implements Window {
    @Override
    public void showWindow() {
        printHeaderPart("Managing Teams as \"" + GlobalData.LOGGED_IN_USERNAME + "\"");
        System.out.println(" ".repeat(64) + "1. Add members to a Sector");
        System.out.println(" ".repeat(64) + "2. Add a custom Sector");
        System.out.println(" ".repeat(64) + "3. View all Sectors and Members");
        System.out.println(" ".repeat(64) + "4. Remove a member from a Sector");
        System.out.println(" ".repeat(64) + "5. Go Back");
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
                    boolean back = false;
                    while (!back) {
                        System.out.println();
                        printCentered("Available Sectors:");
                        teamManager.displaySectorNames();
                        printCentered("Enter 0 to Quit\n");
                        insertPadding("Select the sector number to add a member: ");
                        System.out.print("Select the sector number to add a member: ");
                        int sectorIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (sectorIndex == -1) {
                            back = true;
                        }
                        else if (!teamManager.choiceValidity(sectorIndex)) {
                            printCentered("\n!!! Invalid Sector Number. Please choose a number from the table !!!", YELLOW_TEXT);
                        }
                        else {
                            insertPadding("Enter the member's name: ");
                            System.out.print("Enter the member's name: ");
                            String memberName = scanner.nextLine();
                            teamManager.addMemberToSector(sectorIndex, memberName);
                        }
                    }
                    break;
                case 2:
                    System.out.println();
                    insertPadding("Enter the name of the new sector: ");
                    System.out.print("Enter the name of the new sector: ");
                    String sectorName = scanner.nextLine();
                    teamManager.addCustomSector(sectorName);
                    break;
                case 3:
                    teamManager.displayAllSectors();
                    break;
                case 4:
                    System.out.println("Available Sectors:");
                    teamManager.displaySectorNames();
                    System.out.println("0. Go Back");
                    System.out.print("Select the sector number to remove a member: ");
                    int sectorIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (sectorIndex != -1) {
                        System.out.print("Enter the member's name to remove: ");
                        String memberName = scanner.nextLine();
                        teamManager.removeMemberFromSector(sectorIndex, memberName);
                    }
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
