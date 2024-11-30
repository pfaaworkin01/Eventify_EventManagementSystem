package EventManagement;

import java.util.Scanner;

import static Global.GlobalMethod.printCentered;

public class EventTypeTable {

    public void displayEventTypes () {
        printCentered("+" + "-".repeat(9) + "+" + "-".repeat(20) + "+" + "-".repeat(18) + "+");
        printCentered("|" + " ".repeat(2) + "Label" + " ".repeat(2) + "|" + " ".repeat(5) + "Event Type" + " ".repeat(5) + "|" + " ".repeat(2) + "Event Initials" + " ".repeat(2) + "|");
        printCentered("+" + "-".repeat(9) + "+" + "-".repeat(20) + "+" + "-".repeat(18) + "+");
        printCentered("|" + " ".repeat(4) + "1" + " ".repeat(4) + "|" + " ".repeat(4) + "Institutional" + " ".repeat(3) + "|" + " ".repeat(7) + "INST" + " ".repeat(7) + "|");
        printCentered("+" + "-".repeat(9) + "+" + "-".repeat(20) + "+" + "-".repeat(18) + "+");
        printCentered("|" + " ".repeat(4) + "2" + " ".repeat(4) + "|" + " ".repeat(7) + "Social" + " ".repeat(7) + "|" + " ".repeat(7) + "SOCI" + " ".repeat(7) + "|");
        printCentered("+" + "-".repeat(9) + "+" + "-".repeat(20) + "+" + "-".repeat(18) + "+");
        printCentered("|" + " ".repeat(4) + "3" + " ".repeat(4) + "|" + " ".repeat(6) + "Corporate" + " ".repeat(5) + "|" + " ".repeat(7) + "CORP" + " ".repeat(7) + "|");
        printCentered("+" + "-".repeat(9) + "+" + "-".repeat(20) + "+" + "-".repeat(18) + "+");
    }

    public String selectEventType () {
        String eventType = "NULL";
        boolean quit = false;
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        while (!quit) {
            displayEventTypes();

            int terminalWidth = 150;
            int padding = (terminalWidth - "Choose Event Type (1/2/3): ".length()) / 2;
            for(int i = 0; i < padding; i++) {
                System.out.print(" ");
            }

            System.out.print("Choose Event Type (1/2/3): ");
            selection = scanner.nextInt();
            scanner.nextLine();

            switch (selection) {
                case 1:
                    eventType = "INST";
                    quit = true;
                    break;
                case 2:
                    eventType = "SOCI";
                    quit = true;
                    break;
                case 3:
                    eventType = "CORP";
                    quit = true;
                    break;
                default:
                    printCentered("Invalid Selection. Try Again!!!");
                    break;
            }
        }

        return eventType;
    }

}
