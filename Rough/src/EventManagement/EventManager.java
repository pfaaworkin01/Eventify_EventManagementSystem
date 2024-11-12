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

//    public void displayEvents() {
//        if (events.isEmpty()) {
//            System.out.println("!!! No Events to display !!!");
//        }
//        else {
//            System.out.println("Current Events:");
//            int bulletPoint = 1;
//            for (Event event : events) {
//                System.out.println(bulletPoint + ".\tEvent Name: " + event.getEventName());
//                System.out.println("\tEvent Date: " + event.getEventDate());
//                System.out.println("\tEvent ID: " + event.getEventID() + "\n");
//                bulletPoint++;
//            }
//        }
//    }

}
