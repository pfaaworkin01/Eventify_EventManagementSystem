package EventManagement;

class Event {
    private final int EventID;
    private String EventName;
    private String EventDate;

    public Event(int EventID, String EventName, String EventDate) {
        this.EventID = EventID;
        this.EventName = EventName;
        this.EventDate = EventDate;
    }

    public int getEventID() {
        return EventID;
    }

    public String getEventName() {
        return EventName;
    }

    public String getEventDate() {
        return EventDate;
    }

}