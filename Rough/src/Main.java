import AccessControl.Login;
import AccessControl.Register;
import GlobalData.GlobalData;
import Window.HomeWindow;
import Window.LoggedInWindow;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HomeWindow homeWindow = new HomeWindow();
        LoggedInWindow loggedInWindow = new LoggedInWindow();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {

            homeWindow.showWindow();
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
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

}