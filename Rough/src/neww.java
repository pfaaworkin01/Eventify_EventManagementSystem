import java.io.*;
import java.util.*;

class TeamMember {
    private String name;

    public TeamMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Sector {
    private String sectorName;
    private List<TeamMember> teamMembers;

    public Sector(String sectorName) {
        this.sectorName = sectorName;
        this.teamMembers = new ArrayList<>();
    }

    public void addTeamMember(TeamMember member) {
        teamMembers.add(member);
    }

    public String getSectorName() {
        return sectorName;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }
}

class Event {
    private String eventID;
    private String eventName;
    private Map<String, Sector> sectors;

    public Event(String eventID, String eventName) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.sectors = new HashMap<>();
    }

    public void addSector(String sectorName) {
        sectors.putIfAbsent(sectorName, new Sector(sectorName));
    }

    public void addTeamMemberToSector(String sectorName, String memberName) {
        sectors.computeIfAbsent(sectorName, k -> new Sector(k)).addTeamMember(new TeamMember(memberName));
    }

    public String getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public Map<String, Sector> getSectors() {
        return sectors;
    }
}

class EventManager {
    public static void saveEventData(Event event) {
        String fileName = event.getEventID() + "Event_Data2.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Event ID: " + event.getEventID() + "\n");
            writer.write("Event Name: " + event.getEventName() + "\n\n");

            for (Sector sector : event.getSectors().values()) {
                writer.write("Sector: " + sector.getSectorName() + "\n");
                for (TeamMember member : sector.getTeamMembers()) {
                    writer.write("- " + member.getName() + "\n");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving event data: " + e.getMessage());
        }
    }
}

class Main2 {
    public static void main(String[] args) {
        Event event = new Event("E001", "Tech Conference");
        event.addSector("Security");
        event.addSector("Logistics");
        event.addTeamMemberToSector("Security", "Alice");
        event.addTeamMemberToSector("Logistics", "Bob");
        event.addTeamMemberToSector("Logistics", "Charlie");

        EventManager.saveEventData(event);
        System.out.println("Event data saved successfully!");
    }
}
