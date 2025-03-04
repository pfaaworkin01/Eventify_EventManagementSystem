package EventManagement;

import Global.GlobalMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Global.GlobalData.RESET_ANSI_ESCAPE_CODE_MODIFICATIONS;
import static Global.GlobalData.YELLOW_TEXT;
import static Global.GlobalMethod.printCentered;

public class EventInputHandler {

    public void receiveEventInput() {
        Scanner scanner = new Scanner(System.in);
        EventManager eventManager = new EventManager();

        GlobalMethod.insertPadding("Enter Event ID: ");
        System.out.print("Enter Event ID: ");
        int eventID = scanner.nextInt();
        scanner.nextLine();
        while (!(String.valueOf(eventID).matches("^[0-9]{6}$"))) {
            printCentered(YELLOW_TEXT + "Invalid Event ID. Event ID must strictly be 6 digits long & cannot start with 0. Try Again" + RESET_ANSI_ESCAPE_CODE_MODIFICATIONS);
            GlobalMethod.insertPadding("Enter Event ID: ");
            System.out.print("Enter Event ID: ");
            eventID = scanner.nextInt();
            scanner.nextLine();
        }
        while(!eventIDValid(eventID)) {
            GlobalMethod.insertPadding("Enter Event ID: ");
            System.out.print("Enter Event ID: ");
            eventID = scanner.nextInt();
            scanner.nextLine();
        }

        EventTypeTable eventTypeTable = new EventTypeTable();
        String eventType = eventTypeTable.selectEventType();

        GlobalMethod.insertPadding("Enter Event Name: ");
        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine();

        GlobalMethod.insertPadding("Enter Event Date: ");
        System.out.print("Enter Event Date: ");
        String eventDate = scanner.nextLine();

        eventManager.addNewEvent(eventID, eventType, eventName, eventDate);
    }

    public boolean eventIDValid(int eventIDInput) {
        String StringEventIDInput = String.valueOf(eventIDInput);

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

        for (String[] event : events) {
            if(event[0].equals(StringEventIDInput)) {
                printCentered("Error! Event ID " + StringEventIDInput + " is already in use!");
                printCentered("Event ID must be unique");
                return false;
            }
        }

        return true;
    }



}
