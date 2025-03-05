package Window;

import AccessControl.Login;
import AccessControl.Register;
import java.util.Scanner;
import static Global.GlobalData.*;
import static Global.GlobalMethod.*;

public class HomeWindow implements Window {

    int numberOfLinesToKeepEmpty = 5;

    @Override
    public void showWindow() {
        lineGaps(numberOfLinesToKeepEmpty);

        printHeaderPart("Home");
        insertPadding("1. User Registration", 64);
        insertPadding("2. User Login", 64);
        insertPadding("3. Quit", 64);
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
            int choice;
            if(scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println();
                printCentered("!!!  Dude, only numbers from 1 to 3. It says right there next to the cursor  !!!", YELLOW_TEXT);
                scanner.nextLine();
                numberOfLinesToKeepEmpty = 2;
                continue;
            }

            switch (choice) {
                case 1:
                    Register register = new Register();
                    register.register();
                    break;
                case 2:
                    Login login = new Login();
                    login.login();

                    LoggedInWindow loggedInWindow = new LoggedInWindow();
                    if(AUTHENTICATED) {
                        loggedInWindow.askForInput();
                    }
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println();
                    printCentered("!!!  Seriously Nigga??. U blind or something? Do u see anything assigned to \'" + choice + "\'?", YELLOW_TEXT);
                    numberOfLinesToKeepEmpty = 2;
                    break;
            }
        }
    }
}
