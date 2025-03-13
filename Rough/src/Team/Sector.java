package Team;   // Package containing all the classes related to the team

import java.io.Serializable;
import java.util.List;

import static Global.GlobalData.YELLOW_TEXT;
import static Global.GlobalMethod.*;

abstract class Sector implements Serializable {
    private static final long serialVersionUID = 1L;
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
        if (teamMembers.isEmpty()) {
            System.out.println();
            printCentered("!!! No members assigned to " + sectorName + " yet. !!!", YELLOW_TEXT);
            waitForAnyKey();
        }
        else {
            System.out.println();
            printCentered("Team Members in " + sectorName + ":");
            for (String member : teamMembers) {
                insertPadding("- " + member, 64);
            }
            waitForAnyKey();
        }
    }
}



