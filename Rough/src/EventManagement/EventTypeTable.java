package EventManagement;

import static Global.GlobalMethod.printCentered;

public class EventTypeTable {

    public void displayEventTypes () {
        printCentered("+" + "-".repeat(9) + "+" + "-".repeat(20) + "+");
        printCentered("|" + " ".repeat(2) + "Label" + " ".repeat(2) + "|" + " ".repeat(5) + "Event Type" + " ".repeat(5) + "|" + " ".repeat());
    }

}
