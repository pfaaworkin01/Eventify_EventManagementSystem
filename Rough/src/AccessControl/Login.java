package AccessControl;

import java.io.*;
import java.util.Scanner;

public class Login {

    private static final String LOGIN_CREDENTIALS = "Login_Credentials.txt";

    public void login() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username: ");
        String username = scanner.nextLine();

        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        try(BufferedReader reader = new BufferedReader(new FileReader(LOGIN_CREDENTIALS))) {
            String line;
            boolean found = false;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if(username.equals(parts[0]) && password.equals(parts[1])) {
                    found = true;
                    System.out.println("Logged In Successfully!!!");
                    break;
                }
            }

            if(!found) {
                System.out.println("Invalid Username or Password!!!");
            }
            } catch (IOException e) {
                System.out.println("Error Logging In!!!");
            }

    }

}
