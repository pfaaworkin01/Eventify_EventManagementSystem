package Global;

import java.util.Scanner;

public class GlobalMethod {

    public static void printCentered(String text) {
        int terminalWidth = 154;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

    public static void waitForAnyKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        printCentered("Press \"Enter\" to continue...");
        scanner.nextLine();
    }

    public static void clearConsole() {
        for(int i = 0; i < 8; i++) {
            System.out.println();
        }
    }

    public static void insertPadding() {
        int terminalWidth = 154;
        int padding = (terminalWidth - "Select an Option (1-4): ".length()) / 2;
        for(int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
    }

}
