package Team;   // Package containing all the classes related to the team

import java.util.List;

abstract class Sector {
    private String sectorName;
    private List<String> teamMembers;

    public Sector(String sectorName, List<String> teamMembers) {
        this.sectorName = sectorName;
        this.teamMembers = teamMembers;
    }

    public List<String> getTeamMembers() {
        return teamMembers;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public void setTeamMembers(List<String> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public void addMember(String memberName) {
        teamMembers.add(memberName);
        System.out.println(memberName + " added to " + sectorName);
    }

    public void displayMembers() {
        System.out.println("Team Members in " + sectorName + ":");
        if (teamMembers.isEmpty()) {
            System.out.println("No members in this sector.");
        } else {
            for (String member : teamMembers) {
                System.out.println("- " + member);
            }
        }
    }
}



