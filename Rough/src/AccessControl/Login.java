package AccessControl;

import java.util.Scanner;

import static Global.GlobalData.YELLOW_TEXT;
import static Global.GlobalMethod.insertPadding;
import static Global.GlobalMethod.printCentered;

public class Login {

    private String username;
    private String password;
    int terminalWidth = 154;
    int padding = 0;

    CredentialManager loadCredentials = new CredentialManager();

    public void login() {
        System.out.println();
        int admin1_participant2 = 0;

        System.out.println();
        insertPadding("1. Admin", 68);
        insertPadding("2. Participant", 68);
        insertPadding("3. Go Back\n", 68);
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
                    admin1_participant2 = 1;
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
        this.username = returnUsername();
        this.password = returnPassword();
        loadCredentials.loadCredentials(username, password, admin1_participant2);
    }

    private String returnUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        insertPadding("Enter username: ");
        System.out.print("Enter Username: ");
        return scanner.nextLine();
    }

    private String returnPassword() {
        System.out.println();
        insertPadding("Enter password: ");
        return PasswordField.readPassword("Enter Password: ");
    }
}