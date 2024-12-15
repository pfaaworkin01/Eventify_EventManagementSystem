package EventManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Global.GlobalMethod.printCentered;

public class InputValidator {

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

        while (!StringEventIDInput.matches("^[0-9]{6}$")) {
            printCentered("Invalid Event ID. Event ID must strictly be 6 digits long & cannot start with 0.");
            return false;
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
