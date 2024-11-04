package Team;

import java.util.ArrayList;
import java.util.List;

public class TeamManager {
    private List<Sector> sectors;
    public TeamManager() {
        sectors = new ArrayList<>();
        // Add predefined sectors
        sectors.add(new DevelopmentSector());
        sectors.add(new LogisticsSector());
        sectors.add(new CommunicationSector());
        sectors.add(new CreativeSector());
    }

    public void addCustomSector(String sectorName) {
        if (findSector(sectorName) == null) {
            sectors.add(new CustomSector(sectorName));
            System.out.println("Custom sector '" + sectorName + "' added.");
        } else {
            System.out.println("Sector with this name already exists.");
        }
    }

    public void addMemberToSector(String sectorName, String memberName) {
        Sector sector = findSector(sectorName);
        if (sector != null) {
            sector.addMember(memberName);
        } else {
            System.out.println("Sector not found.");
        }
    }
    public void displayAllSectors() {
        for (Sector sector : sectors) {
            sector.displayMembers();
        }
    }

    public Sector findSector(String sectorName) {
        for (Sector sector : sectors) {
            if (sector.getSectorName().equalsIgnoreCase(sectorName)) {
                return sector;
            }
        }
        return null;
    }








}
