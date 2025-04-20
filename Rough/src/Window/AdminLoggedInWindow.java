package Window;

import Global.GlobalData;
import Global.GlobalMethod;
import java.util.Scanner;

import static Global.GlobalMethod.*;

public class AdminLoggedInWindow implements Window {

    @Override
    public void showWindow() {
        printHeaderPart("Logged In as admin \"" + GlobalData.LOGGED_IN_USERNAME + "\"");
        System.out.println(" ".repeat(67) + "1. Manage Events");
        System.out.println(" ".repeat(67) + "2. Manage Teams");
        System.out.println(" ".repeat(67) + "3. Manage Budget");
        System.out.println(" ".repeat(67) + "4. Log Out\n");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        EventWindow eventWindow = new EventWindow();
        TeamWindow teamWindow = new TeamWindow();

        while (!quit) {
            showWindow();

            for(int i = 0; i < 5; i++) {
                System.out.println();
            }

            GlobalMethod.insertPadding("Select an Option (1-4): ");
            System.out.print("Select an Option (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    eventWindow.askForInput();
                    break;
                case 2:
                    teamWindow.askForInput();
                    if(GlobalData.BACK_TO_MAIN_MENU) {
                        quit = true;
                    }
                    break;
                case 3:
                    BudgetWindow budgetWindow = new BudgetWindow();
                    budgetWindow.askForInput();
                    if(GlobalData.BACK_TO_MAIN_MENU) {
                        quit = true;
                    }
                    break;

                case 4:
                    GlobalData.AUTHENTICATED = false;
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

}
