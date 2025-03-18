package TeamManagement;

import java.io.*;
import java.util.*;

import static Global.GlobalData.*;
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

    public void addCustomSector(int eventID, String sectorName) {
        String filePath = eventID + "_Event_Team_Data.txt";
        File file = new File(filePath);

        Set<String> sectors = new LinkedHashSet<>();

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length > 0) {
                        sectors.add(parts[0]);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading event team data: " + e.getMessage());
            }
        }

        if (!sectors.contains(sectorName)) {
            try (FileWriter writer = new FileWriter(filePath, true)) { // Append mode
                writer.write(sectorName + "\n");
                System.out.println("Sector added successfully.");
            } catch (IOException e) {
                System.err.println("Error saving sector data: " + e.getMessage());
            }
        } else {
            System.out.println("Sector already exists.");
        }
    }

    public void removeTeamMember(int eventID, String sectorName, String memberName) {
        String filePath = eventID + "_Event_Team_Data.txt";
        File file = new File(filePath);

        if (!file.exists()) {
            System.err.println("Event team data file not found.");
            return;
        }

        Map<String, Set<String>> sectorData = new LinkedHashMap<>();

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
            return;
        }

        // Remove member if sector exists
        if (sectorData.containsKey(sectorName)) {
            Set<String> members = sectorData.get(sectorName);
            if (members.remove(memberName)) {
                printCentered("Member \"" + memberName + "\" removed successfully.");
            } else {
                printCentered("Member \"" + memberName + "\" not found in the sector.");
                return;
            }

            // If the sector becomes empty after removal, we can choose to remove it completely
            if (members.isEmpty()) {
                sectorData.remove(sectorName);
            }
        } else {
            System.out.println("Sector not found.");
            return;
        }

        // Write updated data back to file
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Map.Entry<String, Set<String>> entry : sectorData.entrySet()) {
                writer.write(entry.getKey());
                for (String member : entry.getValue()) {
                    writer.write(":" + member);
                }
                writer.write("\n");
            }
            System.out.println("Event team data updated successfully.");
        } catch (IOException e) {
            System.err.println("Error saving updated event team data: " + e.getMessage());
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

        if(maxNumberOfMembers > 0) {
            System.out.println();
            printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+" , BLUE_TEXT);
            printCentered("|" + " ".repeat((int)(Math.floor((totalSectors*20 + totalSectors - 1)-16)/2)) + "Event ID: " + eventID + " ".repeat((int)(Math.ceil((totalSectors*20 + totalSectors - 1)-15)/2)) + "|", BLUE_TEXT);
            printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+" , BLUE_TEXT);
            int padding = (149 - (totalSectors*20+(totalSectors+1))) / 2;
            for (int i = 0; i < maxNumberOfMembers + 1; i++) {
                System.out.print(" ".repeat(padding));
                for (String[] event : eventTeams) {
                    String value = (i < event.length) ? event[i] : "";
                    if(i == 0) {
                        System.out.print(BLUE_TEXT + "|" + " ".repeat((int) Math.floor((20 - value.length()) / 2.0)) + value + " ".repeat((int) Math.ceil((20 - value.length()) / 2.0)) + RESET_ANSI_ESCAPE_CODE_MODIFICATIONS);
                    }
                    else {
                        System.out.print("|" + " ".repeat((int) Math.floor((20 - value.length()) / 2.0)) + value + " ".repeat((int) Math.ceil((20 - value.length()) / 2.0)));
                    }
                }
                if(i == 0) {
                    System.out.println(BLUE_TEXT + "|");
                    printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+", BLUE_TEXT);
                }
                else {
                    System.out.println("|");
                    printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+");
                }
            }
        }
        else {
            System.out.println();
            printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+");
            int padding = (149 - (totalSectors*20+(totalSectors+1))) / 2;
            System.out.print(" ".repeat(padding));
            for (String[] event : eventTeams) {
                System.out.print("|" + " ".repeat((int) Math.floor((20 - event[0].length()) / 2.0)) + event[0] + " ".repeat((int) Math.ceil((20 - event[0].length()) / 2.0)));
            }
            System.out.println("|");
            printCentered("+" + "-".repeat(20 * totalSectors + totalSectors - 1) + "+");
            printCentered("|" + " ".repeat((int)(Math.floor((totalSectors*20 + totalSectors - 1)-15)/2)) + "!!! No Data !!!" + " ".repeat((int)(Math.ceil((totalSectors*20 + totalSectors - 1)-15)/2)) + "|", YELLOW_TEXT);
            printCentered("+" + "-".repeat(20 * totalSectors + totalSectors - 1) + "+");
        }

        waitForAnyKey();

    }

    public void displayEventTeamDataNoWait(int eventID) {
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

        if(maxNumberOfMembers > 0) {
            System.out.println();
            printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+" , BLUE_TEXT);
            printCentered("|" + " ".repeat((int)(Math.floor((totalSectors*20 + totalSectors - 1)-16)/2)) + "Event ID: " + eventID + " ".repeat((int)(Math.ceil((totalSectors*20 + totalSectors - 1)-15)/2)) + "|", BLUE_TEXT);
            printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+" , BLUE_TEXT);
            int padding = (149 - (totalSectors*20+(totalSectors+1))) / 2;
            for (int i = 0; i < maxNumberOfMembers + 1; i++) {
                System.out.print(" ".repeat(padding));
                for (String[] event : eventTeams) {
                    String value = (i < event.length) ? event[i] : "";
                    if(i == 0) {
                        System.out.print(BLUE_TEXT + "|" + " ".repeat((int) Math.floor((20 - value.length()) / 2.0)) + value + " ".repeat((int) Math.ceil((20 - value.length()) / 2.0)) + RESET_ANSI_ESCAPE_CODE_MODIFICATIONS);
                    }
                    else {
                        System.out.print("|" + " ".repeat((int) Math.floor((20 - value.length()) / 2.0)) + value + " ".repeat((int) Math.ceil((20 - value.length()) / 2.0)));
                    }
                }
                if(i == 0) {
                    System.out.println(BLUE_TEXT + "|");
                    printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+", BLUE_TEXT);
                }
                else {
                    System.out.println("|");
                    printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+");
                }
            }
        }
        else {
            System.out.println();
            printCentered(("+" + "-".repeat(20)).repeat(totalSectors) + "+");
            int padding = (149 - (totalSectors*20+(totalSectors+1))) / 2;
            System.out.print(" ".repeat(padding));
            for (String[] event : eventTeams) {
                System.out.print("|" + " ".repeat((int) Math.floor((20 - event[0].length()) / 2.0)) + event[0] + " ".repeat((int) Math.ceil((20 - event[0].length()) / 2.0)));
            }
            System.out.println("|");
            printCentered("+" + "-".repeat(20 * totalSectors + totalSectors - 1) + "+");
            printCentered("|" + " ".repeat((int)(Math.floor((totalSectors*20 + totalSectors - 1)-15)/2)) + "!!! No Data !!!" + " ".repeat((int)(Math.ceil((totalSectors*20 + totalSectors - 1)-15)/2)) + "|", YELLOW_TEXT);
            printCentered("+" + "-".repeat(20 * totalSectors + totalSectors - 1) + "+");
        }

        System.out.println();

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

