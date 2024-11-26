package EventManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Global.GlobalMethod.printCentered;


public class InputValidator {

    public boolean eventIDValid(String eventIDInput) {
        String filePath = "Event_Data.txt";
        List<String[]> events = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] eventDetails = line.split(":");
                events.add(eventDetails);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        while (!eventIDInput.matches("^[0-9]{6}$")) {
            printCentered("Invalid Event ID. Event ID must strictly be 6 digits long.");
            eventIDInput = scanner.nextLine();
        }

        for (String[] event : events) {
            if(event[0].equals(eventIDInput)) {
                printCentered("Error! Event ID " + eventIDInput + " is already in use!");
                printCentered("Event ID must be unique");
                return false;
            }
        }

        return true;
    }



}
