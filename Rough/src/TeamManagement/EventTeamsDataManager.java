package TeamManagement;

import EventManagement.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Global.GlobalData.GREEN_TEXT;
import static Global.GlobalData.RED_TEXT;
import static Global.GlobalMethod.printCentered;
import static Global.GlobalMethod.waitForAnyKey;

public class EventTeamsDataManager {

    public void displayEventTeamData(int eventID) {
        String filePath = eventID + "_Event_Team_Data.txt";
        List<String[]> eventTeams = new ArrayList<>();
        int totalSectors = 0;
        int maxNumberOfMembers = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] eventDetails = line.split(":");
                eventTeams.add(eventDetails);

                totalSectors++;

                int colonCount = line.length() - line.replace(":", "").length();

                if (colonCount > maxNumberOfMembers) {
                    maxNumberOfMembers = colonCount;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println();
        printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+");
        int padding = (149 - (totalSectors*20+5)) / 2;

        for(int i = 0; i < maxNumberOfMembers+1; i++) {
            System.out.print(" ".repeat(padding));
            for (String[] event : eventTeams) {
                System.out.print("|" + " ".repeat((int)Math.floor((20 - event[i].length())/2.0)) + event[i] + " ".repeat((int)Math.ceil((20 - event[i].length())/2.0)));
            }
            System.out.println("|");
            printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+");
        }
        waitForAnyKey();

    }

    public void createEventTeamDataFile(int eventID) {
        String fileName = eventID + "_Event_Team_Data.txt";
        File file = new File(fileName);

        try {
            if (file.exists()) {
                System.out.println();
                printCentered("Event Team Data file already exists. Proceed...", GREEN_TEXT);
                System.out.println();
            } else if (file.createNewFile()) {
                System.out.println();
                addPredefinedSectors(eventID);
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

    private void addPredefinedSectors(int eventID) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(eventID + "_Event_Team_Data.txt", true))) {
            writer.write("Communication:" + "\n");
            writer.write("Creative:" + "\n");
            writer.write("Development:" + "\n");
            writer.write("Logistics:" + "\n");
        } catch (IOException e) {
            printCentered(e.getMessage(), RED_TEXT);
        }

    }

}

