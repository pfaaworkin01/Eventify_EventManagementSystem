package EventManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventDisplay {
    public static void main(String[] args) {
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

        displayTable(events);
    }

    private static void displayTable(List<String[]> events) {
        int idWidth = 30;
        int nameWidth = 50;
        int dateWidth = 30;
        String columnHeader1 = "|" + " ".repeat((idWidth - "Event ID".length())/2) + "Event ID" + " ".repeat((idWidth - "Event ID".length())/2);
        String columnHeader2 = "|" + " ".repeat((nameWidth - "Event Name".length())/2) + "Event Name" + " ".repeat((nameWidth - "Event Name".length())/2);
        String columnHeader3 = "|" + " ".repeat((dateWidth - "Event Date".length())/2) + "Event Date" + " ".repeat((dateWidth - "Event Date".length())/2) + "|";

        printCentered("+" + "-".repeat(idWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");
        printCentered(columnHeader1 + columnHeader2 + columnHeader3);
        printCentered("+" + "-".repeat(idWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");

        for (String[] event : events) {
            String cell1 = "|" + " ".repeat((idWidth - event[0].length())/2) + event[0] + " ".repeat((idWidth - event[0].length())/2);
            String cell2 = "|" + " ".repeat((nameWidth - event[1].length())/2) + event[1] + " ".repeat((nameWidth - event[1].length())/2);
            String cell3 = "|" + " ".repeat((dateWidth - event[2].length())/2) + event[2] + " ".repeat((dateWidth - event[2].length())/2) + "|";
            printCentered(cell1 + cell2 + cell3);
        }

        System.out.println("+" + "-".repeat(idWidth) + "+" + "-".repeat(nameWidth) + "+" + "-".repeat(dateWidth) + "+");
    }

    static void printCentered(String text) {
        int terminalWidth = 150;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }
}
