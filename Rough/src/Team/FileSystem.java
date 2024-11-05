package Team;
import java.io.*;
import java.util.List;

public class FileSystem {
    private static final String FILE_NAME = "teamData.ser";

    // Method to save sectors data to file
    public static void saveData(List<Sector> sectors) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(sectors);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    // Method to load sectors data from file
    public static List<Sector> loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Sector> sectors = (List<Sector>) ois.readObject();
            System.out.println("Data loaded successfully.");
            return sectors;
        } catch (FileNotFoundException e) {
            System.out.println("No existing data found. Starting with default sectors.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return null; // Return null if loading fails
    }
}
