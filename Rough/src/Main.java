import Window.HomeWindow;

import static Global.GlobalMethod.printCentered;

public class Main {

    public static void main(String[] args) {

        HomeWindow homeWindow = new HomeWindow();
        homeWindow.askForInput();

//        TeamWindow window = new TeamWindow();
//        window.askForInput();

    }

//    public static void saveEventTeamData(int eventID, ArrayList<String> teamMemberNames, String sectorName) {
//        String filePath = "new.txt";
//        File file = new File(filePath);
//
//        Map<String, Set<String>> sectorData = new LinkedHashMap<>();
//
//        // Read existing data
//        if (file.exists()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] parts = line.split(":");
//                    if (parts.length > 0) {
//                        String existingSector = parts[0];
//                        Set<String> members = new LinkedHashSet<>();
//                        for (int i = 1; i < parts.length; i++) {
//                            members.add(parts[i]);
//                        }
//                        sectorData.put(existingSector, members);
//                    }
//                }
//            } catch (IOException e) {
//                System.err.println("Error reading event team data: " + e.getMessage());
//            }
//        }
//
//        // Add new data
//        sectorData.putIfAbsent(sectorName, new LinkedHashSet<>());
//        sectorData.get(sectorName).addAll(teamMemberNames);
//
//        // Write updated data back to file
//        try (FileWriter writer = new FileWriter(filePath, false)) { // Overwrite mode
//            for (Map.Entry<String, Set<String>> entry : sectorData.entrySet()) {
//                writer.write(entry.getKey());
//                for (String member : entry.getValue()) {
//                    writer.write(":" + member);
//                }
//                writer.write("\n");
//            }
//            System.out.println("Event team data updated successfully.");
//        } catch (IOException e) {
//            System.err.println("Error saving event team data: " + e.getMessage());
//        }
//    }



}
