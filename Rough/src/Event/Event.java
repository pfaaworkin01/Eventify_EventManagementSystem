package Event;

class Event {
    private String EventName;
    private String EventDate;

    public Event(String EventName, String EventDate) {
        this.EventName = EventName;
        this.EventDate = EventDate;
    }

    public String getEventName() {
        return EventName;
    }

    public String getEventDate() {
        return EventDate;
    }

}

