package Window;

import Team.TeamManager;

import java.util.Scanner;

public class TeamWindow implements Window {
    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        Window.printCentered("EVENTIFY");
        Window.printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        Window.printCentered("Managing Teams\n");
        System.out.println(" ".repeat(65) + "1. Add members to a Sector");
        System.out.println(" ".repeat(65) + "2. Add a custom Sector");
        System.out.println(" ".repeat(65) + "3. View all Sectors and Members");
        System.out.println(" ".repeat(65) + "4. Go Back");
        System.out.println(" ".repeat(65) + "5. Return to Main Menu");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        TeamManager teamManager = new TeamManager();
        HomeWindow homeWindow = new HomeWindow();

        while (!quit) {
            showWindow();
            System.out.println("Select an Option (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the sector name to add a member: ");
                    String sectorName = scanner.nextLine();
                    System.out.print("Enter the member's name: ");
                    String memberName = scanner.nextLine();
                    teamManager.addMemberToSector(sectorName, memberName);
                    break;
                case 2:
                    System.out.print("Enter the name of the new sector: ");
                    sectorName = scanner.nextLine();
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
                    homeWindow.askForInput();
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;

            }
        }




    }
}
