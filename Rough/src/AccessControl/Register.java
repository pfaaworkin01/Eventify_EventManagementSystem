package AccessControl;

import java.util.Scanner;

public class Register {

    CredentialManager saveCredentials = new CredentialManager();

    public void register() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username: ");
        String username = scanner.nextLine();
        while (!username.matches("[a-zA-Z0-9]+")) {
            System.out.println("Invalid Username. Your username can only contain alphanumeric characters!!!");
            username = scanner.nextLine();
        }

        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        while (!password.matches("[a-zA-Z0-9]+")) {
            System.out.println("Invalid Password!!!");
            password = scanner.nextLine();
        }

        saveCredentials.saveCredentials(username, password);

    }

}
