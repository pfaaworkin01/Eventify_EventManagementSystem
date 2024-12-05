package Window;

import Global.GlobalData;

import java.util.Scanner;

import static Global.GlobalMethod.printCentered;

public class LoggedInWindow implements Window {

    public static void clearConsole() {
        for(int i = 0; i < 6; i++) {
            System.out.println();
        }
    }

    @Override
    public void showWindow() {
        clearConsole();

        System.out.println("*".repeat(147));
        printCentered("EVENTIFY");
        printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        printCentered("<<< Logged In >>>\n");
        System.out.println(" ".repeat(65) + "1. Manage Events");
        System.out.println(" ".repeat(65) + "2. Manage Teams");
        System.out.println(" ".repeat(65) + "3. Manage Budget");
        System.out.println(" ".repeat(65) + "4. Log Out\n");
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
            int terminalWidth = 154;
            int padding = (terminalWidth - "Select an Option (1-4): ".length()) / 2;
            for(int i = 0; i < padding; i++) {
                System.out.print(" ");
            }
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
