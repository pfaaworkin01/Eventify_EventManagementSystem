package EventManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    private Map<Integer, Event> events;

    public EventManager() {
        this.events = new HashMap<>();
    }
    DataManager dataManager = new DataManager();

    public void addNewEvent(int EventID, String EventType, String EventName, String EventDate) {
        Event event = new Event(EventID, EventType, EventName, EventDate);
        dataManager.saveData(event);
    }

    public void removeEvent(String EventID) {
        dataManager.deleteEventByID(EventID);
    }

    public void displayAllEvents() {
        dataManager.displayEvents();
    }


    public Event getEventByID(int eventID) {
        return events.get(eventID);
    }


}
