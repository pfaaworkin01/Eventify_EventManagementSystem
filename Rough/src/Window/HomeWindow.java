package Window;

import AccessControl.Login;
import AccessControl.Register;
import GlobalData.GlobalData;

import java.util.Scanner;

public class HomeWindow implements Window {

    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        Window.printCentered("EVENTIFY");
        Window.printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        Window.printCentered("HOME\n");
        System.out.println(" ".repeat(65) + "1. User Registration");
        System.out.println(" ".repeat(65) + "2. User Login");
        System.out.println(" ".repeat(65) + "3. Quit\n");
    }

    @Override
    public void takeInput() {
        LoggedInWindow loggedInWindow = new LoggedInWindow();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            showWindow();
            System.out.println("Select an Option (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Register register = new Register();
                    register.register();
                    break;
                case 2:
                    Login login = new Login();
                    login.login();
                    if(GlobalData.AUTHENTICATED) {
                        loggedInWindow.takeInput();
                    }
                    break;
                case 3:
                    quit = true;

                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }
}
