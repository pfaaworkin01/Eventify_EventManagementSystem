package EventManagement;

import GlobalData.GlobalData;

import java.io.*;

public class DataManager {

    private static final String EVENT_DATA = "Event_Data.txt";

    public void saveData(Event event) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(EVENT_DATA, true))) {
            writer.write(event.getEventID() + ":" + event.getEventType() + ":" + event.getEventName() + ":" + event.getEventDate() + "\n");
            System.out.println(" ".repeat(65) + "Event named " + event.getEventName() + " Created Successfully!!!");
        } catch (IOException e) {
            System.out.println(" ".repeat(65) + "Error Creating New Event!!!");
        }

    }

    public void deleteEventByID(String eventID) {
        File originalFile = new File(EVENT_DATA);
        File tempFile = new File("Temp_Event_Data.txt");

        try (   BufferedReader originalFileReader = new BufferedReader(new FileReader(originalFile));
                BufferedWriter tempFileWriter = new BufferedWriter(new FileWriter(tempFile)) ) {

            String currentLine;
            boolean eventDeleted = false;

            while ((currentLine = originalFileReader.readLine()) != null) {
                if (currentLine.startsWith(eventID + ":")) {
                    eventDeleted = true;
                    continue;
                }
                tempFileWriter.write(currentLine + "\n");
            }

            if (eventDeleted) {
                System.out.println(" ".repeat(65) + "Event with ID \"" + eventID + "\" deleted successfully.");
            } else {
                System.out.println(" ".repeat(65) + "Event with ID \"" + eventID + "\" not found.");
            }
        } catch (IOException e) {
            System.out.println(" ".repeat(65) + "Error Deleting Event. Try Again!");
        }

        if (originalFile.delete()) {
            tempFile.renameTo(originalFile);
        } else {
            System.out.println(" ".repeat(65) + "Failed To Update The Event Data File");
        }

    }

    public void loadData(String username, String password) {

        try(BufferedReader reader = new BufferedReader(new FileReader(EVENT_DATA))) {
            String line;
            boolean found = false;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if(username.equals(parts[0]) && password.equals(parts[1])) {
                    found = true;
                    GlobalData.AUTHENTICATED = true;
                    System.out.println("Logged In Successfully!!!");
                    break;
                }
            }

            if(!found) {
                System.out.println("Invalid Username or Password!!!");
            }

        } catch (IOException e) {
            System.out.println("Error Logging In!!!");
        }

    }

}
