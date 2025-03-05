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

    public void addMemberToSector(int sectorIndex, String memberName) {
        if (sectorIndex >= 0 && sectorIndex < sectors.size()) {
            Sector sector = sectors.get(sectorIndex);
            sector.addMember(memberName);
            FileSystem.saveData(sectors); // Save to file after adding a member
        } else {
            System.out.println("Invalid sector index.");
        }
    }

    public void displayAllSectors() {
        for (Sector sector : sectors) {
            sector.displayMembers();
        }
    }
    public void displaySectorNames() {
        for (int i = 0; i < sectors.size(); i++) {
            System.out.println((i + 1) + ". " + sectors.get(i).getSectorName());
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
}
