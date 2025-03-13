package Team;
import java.io.*;
import java.util.List;

import static Global.GlobalMethod.printCentered;

public class FileSystem {
    private static final String FILE_NAME = "Team_Data.txt";

    public static void saveData(List<Sector> sectors, int eventID) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(eventID + "_" + FILE_NAME))) {
            oos.writeObject(sectors);
            printCentered("Member added successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    // Method to load sectors data from file
    public static List<Sector> loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Sector> sectors = (List<Sector>) ois.readObject();
            return sectors;
        } catch (FileNotFoundException e) {
            System.out.println("No existing data found. Starting with default sectors.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return null; // Return null if loading fails
    }
}
