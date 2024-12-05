package AccessControl;

import Global.GlobalData;

import java.io.*;
import java.io.IOException;

import static Global.GlobalMethod.printCentered;
import static Global.GlobalMethod.waitForAnyKey;

public class CredentialManager {

    private static final String LOGIN_CREDENTIALS = "Login_Credentials.txt";

    public void saveCredentials(String username, String password) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_CREDENTIALS, true))) {
            writer.write(username + ":" + password + "\n");
            System.out.println();
            printCentered("!!!  Registered Successfully  !!!");
            waitForAnyKey();
        } catch (IOException e) {
            printCentered("!!!  Error Registering User  !!!");
        }
    }

    public void loadCredentials(String username, String password) {
        try(BufferedReader reader = new BufferedReader(new FileReader(LOGIN_CREDENTIALS))) {
            String line;
            boolean found = false;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if(username.equals(parts[0]) && password.equals(parts[1])) {
                    found = true;
                    GlobalData.AUTHENTICATED = true;
                    System.out.println();
                    printCentered("!!!  Logged in Successfully  !!!");
                    break;
                }
            }

            if(!found) {
                GlobalData.AUTHENTICATED = false;
                printCentered("!!!  Invalid Username or Password  !!!");
            }
        } catch (IOException e) {
            printCentered("Error Logging In!!!");
        }
    }

    public boolean checkUsernameAvailability(String username) {
        boolean available = true;

        try(BufferedReader reader = new BufferedReader(new FileReader(LOGIN_CREDENTIALS))) {
            String line;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if(username.equals(parts[0])) {
                    available = false;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("!!!  Error Checking Username Availability  !!!");
        }

        return available;
    }

}
