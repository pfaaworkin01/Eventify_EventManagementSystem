package Global;

import java.util.Scanner;

public class GlobalMethod {

    public static void printCentered(String text) {
        int terminalWidth = 154;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

    public static void insertPadding(String text) {
        int terminalWidth = 154;
        int padding = (terminalWidth - text.length()) / 2;
        for(int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
    }

    public static void waitForAnyKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        printCentered("Press any key to continue...");
        scanner.nextLine();
    }

    public static void lineGaps(int numberOfLines) {
        for(int i = 0; i < numberOfLines; i++) {
            System.out.println();
        }
    }

}
