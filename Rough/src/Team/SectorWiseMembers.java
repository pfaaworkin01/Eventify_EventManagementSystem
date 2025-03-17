package Team;

import java.util.Scanner;

import static Global.GlobalData.YELLOW_TEXT;
import static Global.GlobalMethod.insertPadding;
import static Global.GlobalMethod.printCentered;

public class SectorWiseMembers {

    String[] memberNames;

    public void enterMemberNames() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        insertPadding("Enter the number of members. Enter 0 to exit: ");
        System.out.print("Enter the number of members. Enter 0 to exit: ");

        if(scanner.hasNextInt()) {
            int size = scanner.nextInt();
            scanner.nextLine();
            if(size == 0) {
                return;
            }

            memberNames = new String[size];

            for (int i = 0; i < size; i++) {
                System.out.println();
                insertPadding("Enter name for member number " + (i + 1) + ": ");
                System.out.print("Enter name for member number " + (i + 1) + ": ");
                memberNames[i] = scanner.nextLine();
            }
        }
        else {
            System.out.println();
            printCentered("!!! Invalid input. Enter only integers !!!", YELLOW_TEXT);
            enterMemberNames();
        }

        scanner.close();
    }

}
