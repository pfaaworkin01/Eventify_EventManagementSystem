package EventManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public void displayEvents() {
        String filePath = "Event_Data.txt";
        List<String[]> events = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] eventDetails = line.split(":");
                events.add(eventDetails);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        int idWidth = 20;
        int typeWidth = 30;
        int nameWidth = 40;
        int dateWidth = 30;
        String columnHeader1 = "|" + " ".repeat((idWidth - "Event ID".length())/2) + "Event ID" + " ".repeat((idWidth - "Event ID".length())/2);
        String columnHeader2 = "|" + " ".repeat((typeWidth - "Event Type".length())/2) + "Event Type" + " ".repeat((typeWidth - "Event Type".length())/2);
        String columnHeader3 = "|" + " ".repeat((nameWidth - "Event Name".length())/2) + "Event Name" + " ".repeat((nameWidth - "Event Name".length())/2);
        String columnHeader4 = "|" + " ".repeat((dateWidth - "Event Date".length())/2) + "Event Date" + " ".repeat((dateWidth - "Event Date".length())/2) + "|";
        int nameCellPaddingLeft = 0;
        int nameCellPaddingRight = 0;

        printCentered("+" + "-".repeat(idWidth) + "+" + "-".repeat(typeWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");
        printCentered(columnHeader1 + columnHeader2 + columnHeader3 + columnHeader4);
        printCentered("+" + "-".repeat(idWidth) + "+" + "-".repeat(typeWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");

        for (String[] event : events) {
            if (event[2].length() < 20) {
                nameCellPaddingLeft = (20 - event[2].length())/2;
                nameCellPaddingRight = nameCellPaddingLeft;
                if(event[2].length()%2 == 1) {
                    nameCellPaddingRight++;
                }
            }

            String cell1 = "|" + " ".repeat((idWidth - 6)/2) + event[0] + " ".repeat((idWidth - 6)/2);
            String cell2 = "|" + " ".repeat((typeWidth - 4)/2) + event[1] + " ".repeat((typeWidth - 4)/2);
            String cell3 = "|" + " ".repeat((nameWidth - 20)/2) + " ".repeat(nameCellPaddingLeft) + event[2] + " ".repeat(nameCellPaddingRight) + " ".repeat((nameWidth - 20)/2);
            String cell4 = "|" + " ".repeat((dateWidth - 10)/2) + event[3] + " ".repeat((dateWidth - 10)/2) + "|";
            printCentered(cell1 + cell2 + cell3 + cell4);
        }

        printCentered("+" + "-".repeat(idWidth) + "+" + "-".repeat(typeWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");
    }

    static void printCentered(String text) {
        int terminalWidth = 154;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

}
