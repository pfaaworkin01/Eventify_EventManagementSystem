package Window;

import AccessControl.Login;
import AccessControl.Register;

import java.util.Scanner;

public class LoggedInWindow implements Window {

    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        printCentered("EVENTIFY");
        printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        printCentered("Logged In\n");
        System.out.println(" ".repeat(65) + "1. Manage Events");
        System.out.println(" ".repeat(65) + "2. Manage Teams");
        System.out.println(" ".repeat(65) + "3. Log Out\n");
    }

    public void takeInput() {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        EventWindow eventWindow = new EventWindow();

        while (!quit) {
            showWindow();
            System.out.println("Select an Option (1/2): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    eventWindow.showWindow();
                    break;
                case 2:
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }

//        scanner.close();
    }

    public static void printCentered(String text) {
        int terminalWidth = 150;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

}
