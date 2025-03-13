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
        insertPadding("1. Register", 64);
        insertPadding("2. Login", 64);
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
                printCentered("Invalid input. Enter only a number in the range 1-3 ", YELLOW_TEXT);
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
                    ParticipantWindow participantWindow = new ParticipantWindow();
                    if(AUTHENTICATED) {
                        loggedInWindow.askForInput();
                    }
                    else if (PARTICIPANT_AUTHENTICATED) {
                        participantWindow.askForInput();
                    }
                    break;
                case 3:

                    quit = true;
                    break;
                default:
                    System.out.println();
                    printCentered("Select Numbers Assigned to 1-3 only. Nothing is assigned to \'" + choice + "\'", YELLOW_TEXT);
                    printCentered("Invalid choice. Enter a number in the range 1-3", YELLOW_TEXT);
                    numberOfLinesToKeepEmpty = 2;
                    break;
            }
        }
    }


}
