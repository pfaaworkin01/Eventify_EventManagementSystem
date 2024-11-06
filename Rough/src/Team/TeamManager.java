package Team;

import Window.LoggedInWindow;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TeamManager {
    private List<Sector> sectors;

    public TeamManager() {
        sectors = FileSystem.loadData();  // Load data from file, if available

        // If no data is loaded, add predefined sectors
        if (sectors == null) {
            sectors = new ArrayList<>();
            sectors.add(new DevelopmentSector());
            sectors.add(new LogisticsSector());
            sectors.add(new CommunicationSector());
            sectors.add(new CreativeSector());
        }
    }

    public void addCustomSector(String sectorName) {
        if (findSector(sectorName) == null) {
            sectors.add(new CustomSector(sectorName));
            System.out.println("Custom sector '" + sectorName + "' added.");
            FileSystem.saveData(sectors); // Save to file after adding
        } else {
            System.out.println("Sector with this name already exists.");
        }
    }

    public void addMemberToSector(String sectorName, String memberName) {
        Sector sector = findSector(sectorName);
        if (sector != null) {
            sector.addMember(memberName);
            FileSystem.saveData(sectors); // Save to file after adding a member
        } else {
            System.out.println("Sector not found.");
        }
    }

    public void displayAllSectors() {
        for (Sector sector : sectors) {
            sector.displayMembers();
        }
    }

    private Sector findSector(String sectorName) {
        for (Sector sector : sectors) {
            if (sector.getSectorName().equalsIgnoreCase(sectorName)) {
                return sector;
            }
        }
        return null;
    }

    // manageTeams method to handle user interactions
    public void manageTeams() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

                //Change Later
                            LoggedInWindow loggedInWindow = new LoggedInWindow();
                //Change Later

        while (running) {
            System.out.println("\n=== Team Manager ===");
            System.out.println("1. Add Custom Sector");
            System.out.println("2. Add Member to Sector");
            System.out.println("3. Display All Sectors and Members");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the name of the new sector: ");
                    String sectorName = scanner.nextLine();
                    addCustomSector(sectorName);
                }
                case 2 -> {
                    System.out.print("Enter the sector name to add a member: ");
                    String sectorName = scanner.nextLine();
                    System.out.print("Enter the member's name: ");
                    String memberName = scanner.nextLine();
                    addMemberToSector(sectorName, memberName);
                }
                case 3 -> displayAllSectors();
                case 4 -> {
                    loggedInWindow.takeInput();
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
