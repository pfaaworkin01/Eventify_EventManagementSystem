package AccessControl;

import java.util.Scanner;

public class Login {

    private String username;
    private String password;
    int terminalWidth = 154;
    int padding = 0;

    CredentialManager loadCredentials = new CredentialManager();

    public void login() {
        System.out.printf("\n");
        this.username = returnUsername();
        this.password = returnPassword();
        loadCredentials.loadCredentials(username, password);
    }

    private String returnUsername() {
        Scanner scanner = new Scanner(System.in);
        padding = (terminalWidth - "Enter Username: ".length()) / 2;
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print("Enter Username: ");
        return scanner.nextLine();
    }

    private String returnPassword() {
        padding = (terminalWidth - "Enter Password: ".length()) / 2;
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        return PasswordField.readPassword("Enter Password: ");
    }
}