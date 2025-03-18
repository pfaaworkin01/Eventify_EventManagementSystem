import Team.SectorWiseMembers;
import Team.Sectors;
import TeamManagement.EventTeamsDataManager;
import Window.HomeWindow;
import Window.TeamWindow;

import static Global.GlobalMethod.printCentered;

public class Main {

    public static void main(String[] args) {

//        HomeWindow homeWindow = new HomeWindow();
//        homeWindow.askForInput();


        TeamWindow window = new TeamWindow();
        window.askForInput();

    }

}
