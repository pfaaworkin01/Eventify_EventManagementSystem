package Window;

import Team.TeamManager;

public class TeamWindow implements Window {
    @Override
    public void showWindow() {
        TeamManager teamManager = new TeamManager();
        teamManager.manageTeams();
    }

    @Override
    public void takeInput() {

    }
}
