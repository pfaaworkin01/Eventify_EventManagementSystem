package Window;

import GlobalData.GlobalData;

import java.util.Scanner;

public class LoggedInWindow implements Window {

    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        Window.printCentered("EVENTIFY");
        Window.printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        Window.printCentered("Logged In\n");
        System.out.println(" ".repeat(65) + "1. Manage Events");
        System.out.println(" ".repeat(65) + "2. Manage Teams");
        System.out.println(" ".repeat(65) + "3. Log Out\n");
    }

    @Override
    public void askForInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        EventWindow eventWindow = new EventWindow();
        TeamWindow teamWindow = new TeamWindow();

        while (!quit) {
            showWindow();
            System.out.println("Select an Option (1/2): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    eventWindow.askForInput();
                    break;
                case 2:
                    teamWindow.askForInput();
                    if(GlobalData.BACK_TO_MAIN_MENU == true) {
                        quit = true;
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
