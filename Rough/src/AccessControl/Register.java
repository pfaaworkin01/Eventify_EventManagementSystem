package AccessControl;

import java.util.Scanner;

import static Global.GlobalMethod.insertPadding;
import static Global.GlobalMethod.printCentered;

public class Register {

    private String username;
    private String password;
    int terminalWidth = 154;
    int padding = 0;
    Scanner scanner = new Scanner(System.in);


    CredentialManager credentialManager = new CredentialManager();

    public void register() {

        System.out.printf("\n");
        this.username = receiveUsername();

        if(credentialManager.checkUsernameAvailability(this.username)) {
            this.password = receivePassword();
            credentialManager.saveCredentials(username, password);
        }
        else {
            System.out.println();
            printCentered("!!!  Username already exists. Try something different  !!!\n");
            insertPadding("Do you want to continue? (Y/N): ");
            System.out.print("Do you want to continue? (Y/N): ");
            String continueRegistration = scanner.nextLine();
            if(continueRegistration.equalsIgnoreCase("N")) {}
            else {
                register();
            }
        }

    }

    private String receiveUsername() {
        insertPadding("Enter Username: ");
        System.out.print("Enter Username: ");
        String inputUsername = scanner.nextLine();
        while (!inputUsername.matches("[a-zA-Z0-9]+")) {
            printCentered("!!!  Invalid Username. Your username can only contain alphanumeric characters  !!!");
            System.out.printf("\n");
            for(int i = 0; i < padding; i++) {
                System.out.print(" ");
            }
            System.out.print("Enter Username: ");
            inputUsername = scanner.nextLine();
        }

        return inputUsername;
    }

    private String receivePassword() {
        Scanner scanner = new Scanner(System.in);

        insertPadding("Enter Password: ");
        System.out.print("Enter Password: ");
        String inputPassword = scanner.nextLine();
        while (!inputPassword.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,15}")) {
            System.out.println();
            printCentered("!!!  Invalid Password  !!!");
            printCentered("!!!  Password length must be between 8-15  !!!");
            printCentered("!!!  Password also must contain at least one character from each of these groups (a-z), (A-Z), (0-9)  !!!");
            System.out.printf("\n");
            for(int i = 0; i < padding; i++) {
                System.out.print(" ");
            }
            System.out.print("Enter Password: ");
            inputPassword = scanner.nextLine();
        }

        return inputPassword;
    }

}
