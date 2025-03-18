package TeamManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Global.GlobalMethod.printCentered;

public class EventTeamMemberNames {

    String sectorName;
    List<String[]> memberNames = new ArrayList<>();

    public static ArrayList<String> getNames() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> nameList = new ArrayList<>();

        printCentered("Enter names (enter Q/q to stop):");

        while (true) {
            String name = scanner.nextLine();
            if (name.equals("Q") || name.equals("q")) {
                break;
            }
            nameList.add(name);
        }

        return nameList;
    }

}
