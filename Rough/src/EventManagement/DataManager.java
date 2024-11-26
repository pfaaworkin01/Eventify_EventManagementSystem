package EventManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.*;

public class DataManager {

    private static final String EVENT_DATA = "Event_Data.txt";

    public void saveData(Event event) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(EVENT_DATA, true))) {
            writer.write(event.getEventID() + ":" + event.getEventType() + ":" + event.getEventName() + ":" + event.getEventDate() + "\n");
            printCentered("Event named " + event.getEventName() + " Created Successfully!!!");
        } catch (IOException e) {
            System.out.println(" ".repeat(65) + "Error Creating New Event!!!");
        }

    }

    public void deleteEventByID(String eventID) {
        File originalFile = new File(EVENT_DATA);
        File tempFile = new File("Temp_Event_Data.txt");

        try (   BufferedReader originalFileReader = new BufferedReader(new FileReader(originalFile));
                BufferedWriter tempFileWriter = new BufferedWriter(new FileWriter(tempFile)) ) {

            String currentLine;
            boolean eventDeleted = false;

            while ((currentLine = originalFileReader.readLine()) != null) {
                if (currentLine.startsWith(eventID + ":")) {
                    eventDeleted = true;
                    continue;
                }
                tempFileWriter.write(currentLine + "\n");
            }

            if (eventDeleted) {
                System.out.println(" ".repeat(65) + "Event with ID \"" + eventID + "\" deleted successfully.");
            } else {
                System.out.println(" ".repeat(65) + "Event with ID \"" + eventID + "\" not found.");
            }
        } catch (IOException e) {
            System.out.println(" ".repeat(65) + "Error Deleting Event. Try Again!");
        }

        if (originalFile.delete()) {
            tempFile.renameTo(originalFile);
        } else {
            System.out.println(" ".repeat(65) + "Failed To Update The Event Data File");
        }

    }

    static void printCentered(String text) {
        int terminalWidth = 150;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

}
