import Team.TeamManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        Login login = new Login();
        TeamManager teamManager = new TeamManager();

        while (!exit) {
            System.out.println("\n");
            System.out.println("\t\tSelect an Option:\n");
            System.out.println("\t\t1. Register");
            System.out.println("\t\t2. Login");
            System.out.println("\t\t3. Manage Teams");
            System.out.println("\t\t4. Exit");
            System.out.println("\n");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    login.register();
                    break;
                case "2":
                    login.login();
                    break;
                case "3":
                    manageTeams(scanner, teamManager);
                    break;
                case "4":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }

        scanner.close();
    }
    private static void manageTeams(Scanner scanner, TeamManager teamManager) {
        boolean back = false;
        while (!back) {
            System.out.println("\nTeam Management:");
            System.out.println("1. Add Member to Sector");
            System.out.println("2. Create Custom Sector");
            System.out.println("3. Display All Sectors");
            System.out.println("4. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter sector name: ");
                    String sectorName = scanner.nextLine();
                    if (teamManager.findSector(sectorName) != null) {
                        System.out.print("Enter member name: ");
                        String memberName = scanner.nextLine();
                        teamManager.addMemberToSector(sectorName, memberName);
                    } else {
                        System.out.println("Sector does not exist.");
                    }
                    break;
                case 2:
                    System.out.print("Enter custom sector name: ");
                    String customSectorName = scanner.nextLine();
                    teamManager.addCustomSector(customSectorName);
                    break;
                case 3:
                    teamManager.displayAllSectors();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}