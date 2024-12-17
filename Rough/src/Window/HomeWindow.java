package Window;

import AccessControl.Login;
import AccessControl.Register;
import Global.GlobalData;
import java.util.Scanner;

import static Global.GlobalMethod.*;

public class HomeWindow implements Window {

    @Override
    public void showWindow() {
        lineGaps(8);

        System.out.println("*".repeat(154));
        printCentered("EVENTIFY");
        printCentered("Make Every Event Count");
        System.out.println("*".repeat(154));
        printCentered("<<< HOME >>>\n");
        System.out.println(" ".repeat(67) + "1. User Registration");
        System.out.println(" ".repeat(67) + "2. User Login");
        System.out.println(" ".repeat(67) + "3. Quit");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while (!quit) {
            showWindow();

            lineGaps(5);
            insertPadding("Select an Option (1-3): ");
            System.out.print("Select an Option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Register register = new Register();
                    register.register();
                    break;
                case 2:
                    LoggedInWindow loggedInWindow = new LoggedInWindow();
                    Login login = new Login();
                    login.login();
                    if(GlobalData.AUTHENTICATED) {
                        loggedInWindow.askForInput();
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
