package EventManagement;

import Global.GlobalData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Global.GlobalData.*;
import static Global.GlobalMethod.printCentered;
import static Global.GlobalMethod.waitForAnyKey;

public class ParticipantEventRegistration {

    private static final String PARTICIPANT_DATA = "Participant_Data.txt";

    public void registerEvent(String username, int eventID) {
        String filePath = username + "_" + PARTICIPANT_DATA;
        List<String[]> events = new ArrayList<>();

        File file = new File(filePath);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File " + filePath + " not found. Creating a new file.");
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
                return;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (eventID == Integer.parseInt(parts[0])) {
                    printCentered("!!! Already Registered to this event !!!", YELLOW_TEXT);
                    found = true;
                }
            }

            if (!found) {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                    writer.write(eventID + "\n");
                    printCentered("Registered to event " + eventID + " successfully!", GREEN_TEXT);
                } catch (IOException e) {
                    System.out.println(" ".repeat(64) + "Error registering to Event!!!");
                }
            }
        } catch (IOException e) {
            printCentered("Error Registering", RED_TEXT);
        }
    }



}
