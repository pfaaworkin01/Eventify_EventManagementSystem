package AccessControl;

import java.util.Scanner;
import static Global.GlobalData.*;
import static Global.GlobalMethod.*;

public class Register {

    Scanner scanner = new Scanner(System.in);
    CredentialManager credentialManager = new CredentialManager();

    public void register() {
        int admin1_participant2 = 0;

        System.out.println();
        insertPadding("1. Admin", 64);
        insertPadding("2. Participant", 64);
        insertPadding("3. Go Back\n", 64);
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while (!quit) {
            insertPadding("Select an Option (1/2): ");
            System.out.print("Select an Option (1/2): ");
            int choice;
            if(scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println();
                printCentered("Invalid input. Enter only a number in the range 1-3 ", YELLOW_TEXT);
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    if(grantAdminRegistration()) {
                        admin1_participant2 = 1;
                    }
                    else {
                        waitForAnyKey();
                        return;
                    }
                    quit = true;
                    break;
                case 2:
                    admin1_participant2 = 2;
                    quit = true;
                    break;
                case 3:
                    return;
                default:
                    System.out.println();
                    printCentered("Select Numbers Assigned to 1-3 only. Nothing is assigned to \'" + choice + "\'", YELLOW_TEXT);
                    printCentered("Invalid choice. Enter a number in the range 1-3", YELLOW_TEXT);
                    break;
            }
        }

        String username = receiveUsername();

        if (credentialManager.checkUsernameAvailability(username, admin1_participant2)) {
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
                    credentialManager.saveCredentials(username, password, admin1_participant2);
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

    private boolean grantAdminRegistration() {
        boolean granted;
        String adminKey;
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        insertPadding("Enter Key to Register as Admin: ");
        System.out.print("Enter Key to Register as Admin: ");
        adminKey = scanner.nextLine();

        if (adminKey.equals("admin@2025#ADMIN")) {
            granted = true;
            System.out.println();
            printCentered("Access Granted for Admin Registration", GREEN_TEXT);
        }
        else {
            granted = false;
            System.out.println();
            printCentered("Incorrect Admin Key", RED_TEXT);
        }

        return granted;
    }

}