import java.io.*;
import java.util.Scanner;

public class Login {

    private final UserService userService = new UserService();

    public void register() {
        User user = userService.promptUser();
        if (user == null) return;

        try {
            credentialStorage.saveUser(user);
            System.out.println("Registered Successfully!");
        } catch (IOException e) {
            System.out.println("Error Registering User!");
        }
    }

    public void login() {
        User user = userService.promptUser();
        if (user == null) return;

        try {
            if (credentialStorage.checkCredentials(user)) {
                System.out.println("Logged In Successfully!");
            } else {
                System.out.println("Invalid Username or Password!");
            }
        } catch (IOException e) {
            System.out.println("Error Logging In!");
        }
    }
}
