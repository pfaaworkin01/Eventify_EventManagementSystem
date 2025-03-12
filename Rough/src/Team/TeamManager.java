package Team;

import Window.LoggedInWindow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import static Global.GlobalMethod.printCentered;

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
    public void removeMemberFromSector(int sectorIndex, String memberName) {
        if (sectorIndex >= 0 && sectorIndex < sectors.size()) {
            Sector sector = sectors.get(sectorIndex);
            if (sector.getTeamMembers().remove(memberName)) {
                System.out.println(memberName + " removed from " + sector.getSectorName());
                FileSystem.saveData(sectors); // Save to file after removing a member
            } else {
                System.out.println("Member not found in the sector.");
            }
        } else {
            System.out.println("Invalid sector index.");
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

    public void addMemberToSector(int sectorIndex, String memberName) {
            Sector sector = sectors.get(sectorIndex);
            sector.addMember(memberName);
            FileSystem.saveData(sectors);
    }
    public boolean choiceValidity(int choice) {
        if(choice >= 0 && choice < sectors.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void displayAllSectors() {
        for (Sector sector : sectors) {
            sector.displayMembers();
        }
    }
    public void displaySectorNames() {
        printCentered("+" + "-".repeat(7) + "+" + "-".repeat(50) + "+");
        printCentered("|" + "  " + "No.  " + "|" + " ".repeat(19) + "Sector Name" + " ".repeat(20) + "|");
        printCentered("+" + "-".repeat(7) + "+" + "-".repeat(50) + "+");
        for (int i = 0; i < sectors.size(); i++) {
            if(sectors.get(i).getSectorName().length() % 2 == 0) {
                printCentered("|" + "   " + (i + 1) + "   " + "|" + " ".repeat((50 - sectors.get(i).getSectorName().length()) / 2) + sectors.get(i).getSectorName() + " ".repeat((50 - sectors.get(i).getSectorName().length()) / 2) + "|");
                printCentered("+" + "-".repeat(7) + "+" + "-".repeat(50) + "+");
            }
            else {
                printCentered("|" + "   " + (i + 1) + "   " + "|" + " ".repeat((50 - sectors.get(i).getSectorName().length()) / 2) + sectors.get(i).getSectorName() + " ".repeat(((50 - sectors.get(i).getSectorName().length()) / 2) + 1) + "|");
                printCentered("+" + "-".repeat(7) + "+" + "-".repeat(50) + "+");
            }
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

    public int getSectorCount() {
        return sectors.size();
    }
}
