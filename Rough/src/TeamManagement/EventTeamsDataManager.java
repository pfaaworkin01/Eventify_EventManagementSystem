package TeamManagement;

import java.io.*;
import java.util.*;

import static Global.GlobalData.GREEN_TEXT;
import static Global.GlobalData.RED_TEXT;
import static Global.GlobalMethod.*;

public class EventTeamsDataManager {

    public void saveEventTeamData(int eventID, ArrayList<String> teamMemberNames, String sectorName) {
        String filePath = eventID + "_Event_Team_Data.txt";
        File file = new File(filePath);

        Map<String, Set<String>> sectorData = new LinkedHashMap<>();

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length > 0) {
                        String existingSector = parts[0];
                        Set<String> members = new LinkedHashSet<>();
                        for (int i = 1; i < parts.length; i++) {
                            members.add(parts[i]);
                        }
                        sectorData.put(existingSector, members);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading event team data: " + e.getMessage());
            }
        }

        sectorData.putIfAbsent(sectorName, new LinkedHashSet<>());
        sectorData.get(sectorName).addAll(teamMemberNames);

        try (FileWriter writer = new FileWriter(filePath, false)) { // Overwrite mode
            for (Map.Entry<String, Set<String>> entry : sectorData.entrySet()) {
                writer.write(entry.getKey());
                for (String member : entry.getValue()) {
                    writer.write(":" + member);
                }
                writer.write("\n");
            }
            System.out.println("Event team data updated successfully.");
        } catch (IOException e) {
            System.err.println("Error saving event team data: " + e.getMessage());
        }
    }

    public void displayEventTeamData(int eventID) {
        String filePath = eventID + "_Event_Team_Data.txt";
        List<String[]> eventTeams = new ArrayList<>();
        int totalSectors = 0;
        int maxNumberOfMembers = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] eventDetails = line.split(":");
                eventTeams.add(eventDetails);

                totalSectors++;

                int colonCount = line.length() - line.replace(":", "").length();

                if (colonCount > maxNumberOfMembers) {
                    maxNumberOfMembers = colonCount;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println();
        printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+");
        int padding = (149 - (totalSectors*20+5)) / 2;
        for (int i = 0; i < maxNumberOfMembers + 1; i++) {
            System.out.print(" ".repeat(padding));
            for (String[] event : eventTeams) {
                // Use a ternary operator to check if 'i' is within bounds
                String value = (i < event.length) ? event[i] : "";
                System.out.print("|" + " ".repeat((int) Math.floor((20 - value.length()) / 2.0)) + value + " ".repeat((int) Math.ceil((20 - value.length()) / 2.0)));
            }
            System.out.println("|");
            printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+");
        }
        waitForAnyKey();

    }

    public String askToSelectEventSector(int eventID) {
        String filePath = eventID + "_Event_Team_Data.txt";
        List<String[]> eventTeams = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String sectorName = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] eventDetails = line.split(":");
                eventTeams.add(eventDetails);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println();
        printCentered("+" + "-".repeat(5) + "+" + "-".repeat(20) + "+");

        int serial = 1;
        for (String[] event : eventTeams) {
            printCentered("|" + "  " + serial + "  " + "|" + " ".repeat((int)Math.floor((20 - event[0].length())/2.0)) + event[0] + " ".repeat((int)Math.ceil((20 - event[0].length())/2.0)) + "|");
            printCentered("+" + "-".repeat(5) + "+" + "-".repeat(20) + "+");
            serial++;
        }

        System.out.println();
        insertPadding("Select Sector: ");
        System.out.print("Select Sector: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        int sectorNumber = 1;
        for (String[] event : eventTeams) {
            if(choice == sectorNumber) {
                sectorName = event[0];
            }
            sectorNumber++;
        }

        return sectorName;
    }

    public void createEventTeamDataFile(int eventID) {
        String fileName = eventID + "_Event_Team_Data.txt";
        File file = new File(fileName);

        try {
            if (file.exists()) {
                System.out.println();
                printCentered("Event Team Data file already exists. Proceed...", GREEN_TEXT);
                System.out.println();
            } else if (file.createNewFile()) {
                System.out.println();
                addPredefinedSectors(eventID);
                printCentered("Event Team Data file created successfully.", GREEN_TEXT);
                System.out.println();
            } else {
                System.out.println();
                printCentered("!!! Error: Could not create Event Team Data file !!!", RED_TEXT);
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println();
            printCentered("!!! Error creating Event Team Data file: " + e.getMessage() + " !!!", RED_TEXT);
        }
    }

    private void addPredefinedSectors(int eventID) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(eventID + "_Event_Team_Data.txt", true))) {
            writer.write("Communication:" + "\n");
            writer.write("Creative:" + "\n");
            writer.write("Development:" + "\n");
            writer.write("Logistics:" + "\n");
        } catch (IOException e) {
            printCentered(e.getMessage(), RED_TEXT);
        }

    }

}

