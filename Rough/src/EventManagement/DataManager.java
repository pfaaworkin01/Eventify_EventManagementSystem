package EventManagement;

import GlobalData.GlobalData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class DataManager {

    private static final String EVENT_DATA = "Event_Data.txt";

    public void saveData(Event event) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(EVENT_DATA, true))) {
            writer.write(event.getEventID() + ":" + event.getEventType() + ":" + event.getEventName() + ":" + event.getEventDate() + "\n");
            System.out.println(" ".repeat(65) + "Event named " + event.getEventName() + " Created Successfully!!!");
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



    public class EventDisplay {
        public static void main(String[] args) {
            String filePath = "events.txt";  // Replace with your file path
            List<String[]> events = new ArrayList<>();

            // Read file and parse event details
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] eventDetails = line.split(":");
                    events.add(eventDetails);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            // Display data in table format
            displayTable(events);
        }

        private static void displayTable(List<String[]> events) {
            // Define column widths
            int idWidth = 10;
            int nameWidth = 20;
            int dateWidth = 15;

            // Print table header
            System.out.println("+" + "-".repeat(idWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");
            System.out.printf("| %-8s | %-18s | %-13s |\n", "Event ID", "Event Name", "Event Date");
            System.out.println("+" + "-".repeat(idWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");

            // Print each event
            for (String[] event : events) {
                System.out.printf("| %-8s | %-18s | %-13s |\n", event[0], event[1], event[2]);
            }

            // Print table footer
            System.out.println("+" + "-".repeat(idWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");
        }
    }




}
