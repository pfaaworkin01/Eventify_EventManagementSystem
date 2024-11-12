package EventManagement;

import java.util.ArrayList;

public class EventManager {

    private final ArrayList<Event> events;

    public EventManager() {
        events = new ArrayList<>();
    }

    public void addNewEvent(int EventID, String EventType, String EventName, String EventDate) {
        Event newEvent = new Event(EventID, EventType, EventName, EventDate);
        events.add(newEvent);
        System.out.println("Event named \"" + newEvent.getEventName() + "\" added successfully.");
    }

    public void removeEvent(int EventID) {
        for (Event event : events) {
            if (event.getEventID() == EventID) {
                events.remove(event);
                System.out.println("Event named \"" + event.getEventName() + "\" successfully removed.");
                return;
            }
        }
        System.out.println("!!! Invalid Event ID !!!");
    }

    public void displayEvents() {
        if (events.isEmpty()) {
            System.out.println("!!! No Events to display !!!");
        }
        else {
            System.out.println("Current Events:");
            int bulletPoint = 1;
            for (Event event : events) {
                System.out.println(bulletPoint + ".\tEvent Name: " + event.getEventName());
                System.out.println("\tEvent Date: " + event.getEventDate());
                System.out.println("\tEvent ID: " + event.getEventID() + "\n");
                bulletPoint++;
            }
        }
    }

}
