package AccessControl;

import java.util.Scanner;
import static Global.GlobalData.*;
import static Global.GlobalMethod.*;

public class Register {

    Scanner scanner = new Scanner(System.in);
    CredentialManager credentialManager = new CredentialManager();

    public void register() {
        String username = receiveUsername();

        if (credentialManager.checkUsernameAvailability(username)) {
            String password = receivePassword();

            while (!password.isEmpty()) {
                System.out.println();
                insertPadding("Are you sure you want to register as '" + username + "'? (Y/N): ");
                System.out.print("Are you sure you want to register as '" + username + "'? (Y/N): ");
                String confirmRegistration = scanner.nextLine();
                if (confirmRegistration.equalsIgnoreCase("N")) {
                    System.out.println();
                    printCentered("Registration cancelled", YELLOW_TEXT);
                    waitForAnyKey();
                    return;
                } else if (confirmRegistration.equalsIgnoreCase("Y")) {
                    credentialManager.saveCredentials(username, password);
                    return;
                } else {
                    System.out.println();
                    printCentered("Invalid input. Please enter 'Y' or 'N'", YELLOW_TEXT);
                }
            }
        } else {
            System.out.println();
            printCentered("Username already exists. Try something different", YELLOW_TEXT);

            while (true) {
                System.out.println();
                insertPadding("Do you want to continue? (Y/N): ");
                System.out.print("Do you want to continue? (Y/N): ");
                String continueRegistration = scanner.nextLine();
                if (continueRegistration.equalsIgnoreCase("N")) {
                    System.out.println();
                    printCentered("Registration cancelled", YELLOW_TEXT);
                    waitForAnyKey();
                    break;
                } else if (continueRegistration.equalsIgnoreCase("Y")) {
                    register();
                    break;
                } else {
                    System.out.println();
                    printCentered("Invalid input. Please enter 'Y' or 'N'", YELLOW_TEXT);
                }
            }
        }
    }

    private String receiveUsername() {
        System.out.println();
        insertPadding("Enter Username: ");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        if (!username.matches("[a-zA-Z0-9]+")) {
            System.out.println();
            printCentered("Invalid Username. Only alphanumeric characters are allowed", YELLOW_TEXT);
            return receiveUsername();
        }
        return username;
    }

    private String receivePassword() {
        System.out.println();
        insertPadding("Enter Password: ");
        String inputPassword = PasswordField.readPassword("Enter Password: ");
        int count = 0;
        while (!inputPassword.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,15}")) {
            System.out.println();
            printCentered("!!!  Invalid Password  !!!", YELLOW_TEXT);
            printCentered("!!!  Password length must be between 8-15  !!!", YELLOW_TEXT);
            printCentered("!!!  Password also must contain at least one character from each of these groups (a-z), (A-Z), (0-9)  !!!\n", YELLOW_TEXT);
            count++;
            while (count > 2) {
                insertPadding("Do you want to continue? (Y/N): ");
                System.out.print("Do you want to continue? (Y/N): ");
                String continueRegistration = scanner.nextLine();
                if (continueRegistration.equalsIgnoreCase("N")) {
                    System.out.println();
                    printCentered("Registration cancelled", YELLOW_TEXT);
                    waitForAnyKey();
                    break;
                } else if (continueRegistration.equalsIgnoreCase("Y")) {
                    receivePassword();
                    break;
                } else {
                    System.out.println();
                    printCentered("Invalid input. Please enter 'Y' or 'N'", YELLOW_TEXT);
                }
            }
            if(count <= 2) {
                insertPadding("Enter Password: ");
                inputPassword = PasswordField.readPassword("Enter Password: ");
            }
            else {
                inputPassword = "";
                break;
            }
        }
        return inputPassword;
    }
}