package TeamManagement;

import java.util.ArrayList;
import java.util.List;

class Sectors {
    private String sectorName;
    private List<TeamMember> teamMembers;

    public Sectors(String sectorName) {
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
