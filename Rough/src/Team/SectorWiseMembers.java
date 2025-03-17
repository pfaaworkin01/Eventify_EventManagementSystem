package Team;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Global.GlobalData.GREEN_TEXT;
import static Global.GlobalData.YELLOW_TEXT;
import static Global.GlobalMethod.insertPadding;
import static Global.GlobalMethod.printCentered;

public class SectorWiseMembers {

    private List<String> memberNames;

    public List<String> enterMemberNames() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            insertPadding("Enter member name. Enter 0 to exit: ");
            System.out.print("Enter member name. Enter 0 to exit: ");
            String memberName = scanner.nextLine();
            if(memberName.equals("0")) {
                break;
            }

            if (!memberNames.contains(memberName)) {
                memberNames.add(memberName);
                System.out.println();
                printCentered("Member \"" + memberName + "\" added", GREEN_TEXT);
            }
            else {
                System.out.println();
                printCentered("Member name already exists. Try Again !!!", YELLOW_TEXT);
            }
        }

        scanner.close();

        return memberNames;
    }

}

