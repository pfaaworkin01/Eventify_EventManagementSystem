package EventManagement;

import Global.GlobalData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Global.GlobalData.*;
import static Global.GlobalMethod.printCentered;
import static Global.GlobalMethod.waitForAnyKey;

public class ParticipantEventRegistration {

    private static final String PARTICIPANT_DATA = "Participant_Data.txt";

    public void registerEvent(String username, int eventID) {
        String filePath = username + "_" + PARTICIPANT_DATA;

        File file = new File(filePath);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    printCentered("File " + filePath + " not found. Creating a new file.");
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

    public void unregisterEvent(String username, int eventID) {
        String filePath = username + "_" + PARTICIPANT_DATA;
        File file = new File(filePath);

        if (!file.exists()) {
            printCentered("No registration file found for " + username, YELLOW_TEXT);
            return;
        }

        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && Integer.parseInt(line.trim()) == eventID) {
                    found = true;
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            printCentered("Error reading file", RED_TEXT);
            return;
        }

        if (!found) {
            printCentered("Event ID " + eventID + " not found in registration.", YELLOW_TEXT);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine + "\n");
            }
            printCentered("Successfully unregistered from event " + eventID + "!", GREEN_TEXT);
        } catch (IOException e) {
            printCentered("Error updating registration file", RED_TEXT);
        }
    }

    public void displayRegisteredEvents(String username) {
        String eventFilePath = "Event_Data.txt";
        String participantFilePath = username + "_" + PARTICIPANT_DATA;
        List<String[]> events = new ArrayList<>();
        Set<String> registeredEventIDs = new HashSet<>();

        // Read registered event IDs
        File participantFile = new File(participantFilePath);
        if (participantFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(participantFilePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    registeredEventIDs.add(line.trim());
                }
            } catch (IOException e) {
                printCentered("Error reading participant file", RED_TEXT);
                return;
            }
        } else {
            printCentered("No registered events found for " + username, YELLOW_TEXT);
            return;
        }

        // Read all events
        try (BufferedReader br = new BufferedReader(new FileReader(eventFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] eventDetails = line.split(":");
                if (registeredEventIDs.contains(eventDetails[0].trim())) {
                    events.add(eventDetails);
                }
            }
        } catch (IOException e) {
            printCentered("Error reading event file", RED_TEXT);
            return;
        }

        if (events.isEmpty()) {
            printCentered("No registered events to display.", YELLOW_TEXT);
            return;
        }

        int idWidth = 20;
        int typeWidth = 30;
        int nameWidth = 40;
        int dateWidth = 30;
        int padding = idWidth + typeWidth + nameWidth + dateWidth + 3;
        printCentered("+" + "-".repeat(idWidth) + "+" + "-".repeat(typeWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+", BLUE_TEXT);
        printCentered("|" + " ".repeat((int)Math.floor(padding - (username.length() + 12))/2) + "Username: \"" + username + "\"" + " ".repeat((int)Math.ceil(padding - (username.length() + 12))/2) + "|", BLUE_TEXT);
        printCentered("+" + "-".repeat(idWidth) + "+" + "-".repeat(typeWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+", BLUE_TEXT);
        printCentered(String.format("| %-18s | %-28s | %-38s | %-28s |", "Event ID", "Event Type", "Event Name", "Event Date"), BLUE_TEXT);
        printCentered("+" + "-".repeat(idWidth) + "+" + "-".repeat(typeWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+", BLUE_TEXT);

        for (String[] event : events) {
            printCentered(String.format("| %-18s | %-28s | %-38s | %-28s |", event[0], event[1], event[2], event[3]));
        }

        printCentered("+" + "-".repeat(idWidth) + "+" + "-".repeat(typeWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");

    }
}
