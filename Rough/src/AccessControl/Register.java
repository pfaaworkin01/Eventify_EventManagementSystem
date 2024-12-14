package AccessControl;

import java.util.Scanner;

import static Global.GlobalMethod.printCentered;

public class Register {

    private String username;
    private String password;
    int terminalWidth = 154;
    int padding = 0;

    CredentialManager saveCredentials = new CredentialManager();

    public void register() {

        System.out.printf("\n");
        this.username = returnUsername();

        if(saveCredentials.checkUsernameAvailability(this.username)) {
            this.password = returnPassword();
            saveCredentials.saveCredentials(username, password);
        }
        else {
            printCentered("!!!  Username exists. Try something different  !!!");
            register();
        }

    }

    private String returnUsername() {
        Scanner scanner = new Scanner(System.in);
        padding = (terminalWidth - "Enter Username: ".length()) / 2;
        for(int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
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

    private String returnPassword() {
        Scanner scanner = new Scanner(System.in);
        padding = (terminalWidth - "Enter Password: ".length()) / 2;
        for(int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
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
