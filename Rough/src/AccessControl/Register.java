package AccessControl;

import java.util.Scanner;

import static Global.GlobalMethod.printCentered;

public class Register {

    private String username;
    private String password;
    int terminalWidth = 150;
    int padding = 0;

    CredentialManager saveCredentials = new CredentialManager();

    public void register() {

        this.username = returnUsername();

        if(saveCredentials.checkUsernameAvailability(this.username)) {
            this.password = returnPassword();
            saveCredentials.saveCredentials(username, password);
        }
        else {
            printCentered("Username exists!!! Try using another one");
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
            printCentered("Invalid Username. Your username can only contain alphanumeric characters!!!");
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
        while (!inputPassword.matches(".*[a-z].*") || !inputPassword.matches(".*[A-Z].*") || !inputPassword.matches(".*[0-9].*")) {
            printCentered("Invalid Password!!! Password must contain at least one lowercase letter (a-z), one uppercase letter(A-Z), and one digit(0-9)");
            for(int i = 0; i < padding; i++) {
                System.out.print(" ");
            }
            System.out.print("Enter Password: ");
            inputPassword = scanner.nextLine();
        }

        return inputPassword;
    }

}
