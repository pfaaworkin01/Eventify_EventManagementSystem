package TeamManagement;

import EventManagement.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static Global.GlobalData.GREEN_TEXT;
import static Global.GlobalData.RED_TEXT;
import static Global.GlobalMethod.printCentered;

public class EventTeamsDataManager {
    public static void createEventTeamDataFile(int eventID) {
        String fileName = eventID + "_Event_Team_Data.txt";
        File file = new File(fileName);

        try {
            if (file.exists()) {
                System.out.println();
                printCentered("Event Team Data file already exists. Proceed...", GREEN_TEXT);
                System.out.println();
            } else if (file.createNewFile()) {
                System.out.println();
                printCentered("Event Team Data file created successfully.", GREEN_TEXT);
                System.out.println();
            } else {
                System.out.println();
                printCentered("!!! Error: Could not create Event Team Data file !!!", RED_TEXT);
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println();
            printCentered("!!! Error creating Event Team Data file: " + e.getMessage() + " !!!", RED_TEXT);
        }
    }

    public void addPredefinedSectors(int eventID) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(eventID + "_Event_Team_Data.txt", true))) {
            writer.write("Communication:" + ";\n");
            writer.write("Creative:" + ";\n");  
        } catch (IOException e) {
            System.out.println(" ".repeat(65) + "Error Creating New Event!!!");
        }

    }

}

