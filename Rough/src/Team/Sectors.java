package Team;

import java.util.*;

public class Sectors {

    private List<String> sectorNames;
    private List<String> memberNames;
    SectorWiseMembers members = new SectorWiseMembers();

    public Sectors() {
        sectorNames = new ArrayList<>();
        sectorNames.add("Communication");
        sectorNames.add("Creative");
        sectorNames.add("Development");
        sectorNames.add("Logistics");
        memberNames = members.enterMemberNames();
    }

    public void addCustomSector(String customSectorName) {
        sectorNames.add(customSectorName);
    }

}
