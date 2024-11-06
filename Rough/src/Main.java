import AccessControl.Login;
import AccessControl.Register;
import GlobalData.GlobalData;
import Window.HomeWindow;
import Window.LoggedInWindow;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HomeWindow homeWindow = new HomeWindow();
        LoggedInWindow loggedInWindow = new LoggedInWindow();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {

            homeWindow.showWindow();
            System.out.println("Select an Option (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Register register = new Register();
                    register.register();
                    break;
                case 2:
                    Login login = new Login();
                    login.login();
                    if(GlobalData.AUTHENTICATED) {
                        loggedInWindow.takeInput();
                    }
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }

        scanner.close();

    }
//    private static void manageTeams(Scanner scanner, TeamManager teamManager) {
//        boolean back = false;
//        while (!back) {
//            System.out.println("\nTeam Management:");
//            System.out.println("1. Add Member to Sector");
//            System.out.println("2. Create Custom Sector");
//            System.out.println("3. Display All Sectors");
//            System.out.println("4. Back to Main Menu");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter sector name: ");
//                    String sectorName = scanner.nextLine();
//                    if (teamManager.findSector(sectorName) != null) {
//                        System.out.print("Enter member name: ");
//                        String memberName = scanner.nextLine();
//                        teamManager.addMemberToSector(sectorName, memberName);
//                    } else {
//                        System.out.println("Sector does not exist.");
//                    }
//                    break;
//                case 2:
//                    System.out.print("Enter custom sector name: ");
//                    String customSectorName = scanner.nextLine();
//                    teamManager.addCustomSector(customSectorName);
//                    break;
//                case 3:
//                    teamManager.displayAllSectors();
//                    break;
//                case 4:
//                    back = true;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        }
//    }
}