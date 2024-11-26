package EventManagement;

import java.util.ArrayList;

public class EventManager {

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

}
