package Window;

import Global.GlobalData;
import Team.TeamManager;

import java.util.Scanner;

import static Global.GlobalMethod.printCentered;

public class TeamWindow implements Window {
    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(154));
        printCentered("EVENTIFY");
        printCentered("Make Every Event Count");
        System.out.println("*".repeat(154));
        printCentered("Managing Teams\n");
        System.out.println(" ".repeat(67) + "1. Add members to a Sector");
        System.out.println(" ".repeat(67) + "2. Add a custom Sector");
        System.out.println(" ".repeat(67) + "3. View all Sectors and Members");
        System.out.println(" ".repeat(67) + "4. Go Back");
        System.out.println(" ".repeat(67) + "5. Return to Main Menu");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        TeamManager teamManager = new TeamManager();

        while (!quit) {
            showWindow();

            int terminalWidth = 154;
            int padding = (terminalWidth - "Select an Option (1-5): ".length()) / 2;
            for(int i = 0; i < padding; i++) {
                System.out.print(" ");
            }
            System.out.print("Select an Option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    boolean back = false;
                    while (!back) {
                        System.out.println("Available Sectors:");
                        teamManager.displaySectorNames();
                        System.out.println("0. Go Back");
                        System.out.print("Select the sector number to add a member: ");
                        int sectorIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (sectorIndex == -1) {
                            back = true;
                        } else {
                            System.out.print("Enter the member's name: ");
                            String memberName = scanner.nextLine();
                            teamManager.addMemberToSector(sectorIndex, memberName);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter the name of the new sector: ");
                    String sectorName = scanner.nextLine();
                    teamManager.addCustomSector(sectorName);
                    break;
                case 3:
                    teamManager.displayAllSectors();
                    break;
                case 4:
                    quit = true;
                    break;
                case 5:
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
