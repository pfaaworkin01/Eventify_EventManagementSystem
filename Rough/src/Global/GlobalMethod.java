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

}
