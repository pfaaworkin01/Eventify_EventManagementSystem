package AccessControl;

import Global.GlobalData;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import static Global.GlobalData.*;
import static Global.GlobalMethod.printCentered;
import static Global.GlobalMethod.waitForAnyKey;

public class CredentialManager {

    private static final String ADMIN_LOGIN_CREDENTIALS = "Admin_Login_Credentials.txt";
    private static final String PARTICIPANT_LOGIN_CREDENTIALS = "Participant_Login_Credentials.txt";

    public void saveCredentials(String username, String password, int admin1_participant2) {
        if(admin1_participant2 == 1) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_LOGIN_CREDENTIALS, true))) {
                String salt = generateSalt();
                String hashedPassword = hashPassword(password, salt);
                writer.write(username + ":" + salt + ":" + hashedPassword + "\n");
                System.out.println();
                printCentered("Registered Successfully", GREEN_TEXT);
                waitForAnyKey();
            } catch (IOException e) {
                printCentered("Error Registering User, try again", RED_TEXT);
            }
        }
        else if (admin1_participant2 == 2) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PARTICIPANT_LOGIN_CREDENTIALS, true))) {
                String salt = generateSalt();
                String hashedPassword = hashPassword(password, salt);
                writer.write(username + ":" + salt + ":" + hashedPassword + "\n");
                System.out.println();
                printCentered("Registered Successfully", GREEN_TEXT);
                waitForAnyKey();
            } catch (IOException e) {
                printCentered("Error Registering User, try again", RED_TEXT);
            }
        }

    }

    public void loadCredentials(String username, String password, int admin1_participant2) {
        if(admin1_participant2 == 1) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_LOGIN_CREDENTIALS))) {
                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (username.equals(parts[0])) {
                        String salt = parts[1];
                        String storedHash = parts[2];
                        if (checkPassword(password, salt, storedHash)) {
                            found = true;
                            GlobalData.AUTHENTICATED = true;
                            System.out.println();
                            printCentered("!!!  Logged in Successfully  !!!");
                            GlobalData.LOGGED_IN_USERNAME = username;
                            waitForAnyKey();
                            break;
                        }
                    }
                }

                if (!found) {
                    GlobalData.AUTHENTICATED = false;
                    printCentered("Invalid Username or Password. Try Again", YELLOW_TEXT);
                }
            } catch (IOException e) {
                printCentered("Error Logging In", RED_TEXT);
            }
        }
        else if(admin1_participant2 == 2) {
            try (BufferedReader reader = new BufferedReader(new FileReader(PARTICIPANT_LOGIN_CREDENTIALS))) {
                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (username.equals(parts[0])) {
                        String salt = parts[1];
                        String storedHash = parts[2];
                        if (checkPassword(password, salt, storedHash)) {
                            found = true;
                            PARTICIPANT_AUTHENTICATED = true;
                            System.out.println();
                            printCentered("!!!  Logged in Successfully  !!!");
                            LOGGED_IN_USERNAME = username;
                            waitForAnyKey();
                            break;
                        }
                    }
                }

                if (!found) {
                    PARTICIPANT_AUTHENTICATED = false;
                    printCentered("Invalid Username or Password. Try Again", YELLOW_TEXT);
                }
            } catch (IOException e) {
                printCentered("Error Logging In", RED_TEXT);
            }
        }
    }

    public boolean checkUsernameAvailability(String username, int admin1_participant2) {
        boolean available = true;

        if(admin1_participant2 == 1) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_LOGIN_CREDENTIALS))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (username.equals(parts[0])) {
                        available = false;
                        break;
                    }
                }
            } catch (IOException e) {
                printCentered("Credentials file not found. Creating new file...", RED_TEXT);
            }
        }
        else if (admin1_participant2 == 2) {
            try (BufferedReader reader = new BufferedReader(new FileReader(PARTICIPANT_LOGIN_CREDENTIALS))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (username.equals(parts[0])) {
                        available = false;
                        break;
                    }
                }
            } catch (IOException e) {
                printCentered("Credentials file not found. Creating new file...", RED_TEXT);
            }
        }

        return available;
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPassword = salt + password;
            byte[] hash = digest.digest(saltedPassword.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing password hashing", e);
        }
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        StringBuilder hexString = new StringBuilder();

        for (byte b : saltBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private boolean checkPassword(String inputPassword, String salt, String storedHash) {
        String hashedInput = hashPassword(inputPassword, salt);
        return hashedInput.equals(storedHash);
    }

}


