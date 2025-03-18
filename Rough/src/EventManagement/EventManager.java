package EventManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private Map<Integer, Event> events;

    public EventManager() {
        this.events = new HashMap<>();
        loadEvents();
    }
    EventDataManager eventDataManager = new EventDataManager();

    public void addNewEvent(int EventID, String EventType, String EventName, String EventDate) {
        Event event = new Event(EventID, EventType, EventName, EventDate);
        eventDataManager.saveData(event);
    }

    public void removeEvent(String EventID) {
        eventDataManager.deleteEventByID(EventID);
    }

    public void displayAllEvents() {
        eventDataManager.displayEvents();
    }


    public Event getEventByID(int eventID) {
        return events.get(eventID);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events.values());
    }

    private void loadEvents() {
        String filePath = "Event_Data.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] eventDetails = line.split(":");
                int eventID = Integer.parseInt(eventDetails[0]);
                String eventType = eventDetails[1];
                String eventName = eventDetails[2];
                String eventDate = eventDetails[3];
                Event event = new Event(eventID, eventType, eventName, eventDate);
                events.put(eventID, event);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void displayRegisteredUsersForEvent(int eventID) {
        ParticipantEventRegistration registration = new ParticipantEventRegistration();
        List<String> registeredUsers = registration.getRegisteredUsersForEvent(eventID);

        String BLUE_TEXT = "\u001B[34m";
        String GREEN_TEXT = "\u001B[32m";
        String RESET_TEXT = "\u001B[0m";

        if (registeredUsers.isEmpty()) {
            printCentered(GREEN_TEXT + "No users registered for event ID: " + eventID + RESET_TEXT);
        } else {
            printCentered("Registered users for event ID " + eventID + ":" + RESET_TEXT);
            printCentered(BLUE_TEXT + "+" + "-".repeat(30) + "+" + RESET_TEXT);
            printCentered(GREEN_TEXT + "| " + center("Username", 28) + " |" + RESET_TEXT);
            printCentered(BLUE_TEXT + "+" + "-".repeat(30) + "+" + RESET_TEXT);
            for (String user : registeredUsers) {
                printCentered(BLUE_TEXT + "| " + center(user, 28) + " |" + RESET_TEXT);
            }
            printCentered(BLUE_TEXT + "+" + "-".repeat(30) + "+" + RESET_TEXT);
        }
    }

    private String center(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int leftPadding = (width - text.length()) / 2;
        int rightPadding = width - text.length() - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }
    public static void printCentered(String text) {
        int terminalWidth = 149;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }
}
