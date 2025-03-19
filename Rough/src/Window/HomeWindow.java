package Window;

import AccessControl.Login;
import AccessControl.Register;
import java.util.Scanner;
import static Global.GlobalData.*;
import static Global.GlobalMethod.*;

public class HomeWindow implements Window {

    @Override
    public void showWindow() {
        headClearance(5);

        printHeaderPart("Home");
        insertPadding("1. Register", 68);
        insertPadding("2. Login", 68);
        insertPadding("3. Quit", 68);
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        boolean showWindow = true;

        while(!quit) {

            if(showWindow) {
                showWindow();
            }

            System.out.println();
            insertPadding("Select an Option (1-3): ");
            System.out.print("Select an Option (1-3): ");
            int choice;
            if(scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            else {
                System.out.println();
                printCentered("!!! Invalid input. Enter only a number in the range 1-3 !!!", YELLOW_TEXT);
                scanner.nextLine();
                showWindow = false;
                continue;
            }

            switch(choice) {
                case 1:
                    Register register = new Register();
                    register.register();
                    break;
                case 2:
                    Login login = new Login();
                    login.login();
                    AdminLoggedInWindow adminLoggedInWindow = new AdminLoggedInWindow();
                    ParticipantLoggedInWindow participantLoggedInWindow = new ParticipantLoggedInWindow();
                    if(AUTHENTICATED) {
                        adminLoggedInWindow.askForInput();
                    }
                    else if (PARTICIPANT_AUTHENTICATED) {
                        participantLoggedInWindow.askForInput();
                    }
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println();
                    printCentered("!!! Invalid choice. Enter a number in the range 1-3 !!!", YELLOW_TEXT);
                    showWindow = false;
                    break;
            }
        }
    }


}
