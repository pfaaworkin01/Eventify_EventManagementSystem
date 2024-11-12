package EventManagement;

public class Event {
    private final int EventID;
    private final String EventType;
    private String EventName;
    private String EventDate;

    public Event(int EventID, String EventType, String EventName, String EventDate) {
        this.EventID = EventID;
        this.EventType = EventType;
        this.EventName = EventName;
        this.EventDate = EventDate;
    }

    public int getEventID() {
        return EventID;
    }

    public String getEventType() {
        return EventType;
    }

    public String getEventName() {
        return EventName;
    }

    public String getEventDate() {
        return EventDate;
    }

}