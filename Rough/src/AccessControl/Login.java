package AccessControl;

import java.util.Scanner;

public class Login {

    CredentialManager loadCredentials = new CredentialManager();

    public void login() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username: ");
        String username = scanner.nextLine();

        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        loadCredentials.loadCredentials(username, password);

    }

}
