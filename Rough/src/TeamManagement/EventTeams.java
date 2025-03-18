package TeamManagement;

import java.util.HashMap;
import java.util.Map;

class EventTeams {
    private String eventID;
    private Map<String, Sectors> sectors;

    public EventTeams(String eventID) {
        this.eventID = eventID;
        this.sectors = new HashMap<>();
    }

    public void addSector(String sectorName) {
        sectors.putIfAbsent(sectorName, new Sectors(sectorName));
    }

    public void addTeamMemberToSector(String sectorName, String memberName) {
        sectors.computeIfAbsent(sectorName, k -> new Sectors(k)).addTeamMember(new TeamMember(memberName));
    }

    public String getEventID() {
        return eventID;
    }

    public Map<String, Sectors> getSectors() {
        return sectors;
    }

}
