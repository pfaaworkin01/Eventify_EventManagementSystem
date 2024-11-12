package EventManagement;

import GlobalData.GlobalData;

import java.io.*;

public class DataManager {

    private static final String LOGIN_CREDENTIALS = "Event_Data.txt";

    public void saveCredentials(String username, String password) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_CREDENTIALS, true))) {
            writer.write(username + ":" + password + "\n");
            System.out.println("Registered Successfully!!!");
        } catch (IOException e) {
            System.out.println("Error Registering User!!!");
        }
    };

    public void loadCredentials(String username, String password) {
        try(BufferedReader reader = new BufferedReader(new FileReader(LOGIN_CREDENTIALS))) {
            String line;
            boolean found = false;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if(username.equals(parts[0]) && password.equals(parts[1])) {
                    found = true;
                    GlobalData.AUTHENTICATED = true;
                    System.out.println(GlobalData.AUTHENTICATED);
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
    };

}
