package Global;

import java.util.Scanner;

import static Global.GlobalData.*;

public class GlobalMethod {

    public static void printHeaderPart(String windowTitle) {
        System.out.println("*".repeat(154));
        System.out.print(BLUE_BACKGROUND + BLACK_TEXT + BOLD_TEXT);
        printCentered("EVENTIFY");
        printCentered("Make Every Event Count");
        System.out.print(RESET_ANSI_ESCAPE_CODE_MODIFICATIONS);
        System.out.println("*".repeat(154));
        printCentered("<<< " + windowTitle + " >>>", BLUE_TEXT);
        System.out.println();
    }

    public static void printCentered(String text) {
        int terminalWidth = 154;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

    public static void printCentered(String text, String textColor) {
        System.out.print(textColor);
        int terminalWidth = 154;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
        System.out.print(RESET_ANSI_ESCAPE_CODE_MODIFICATIONS);
    }

    public static void insertPadding(String text) {
        int terminalWidth = 154;
        int padding = (terminalWidth - text.length()) / 2;
        for(int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
    }

    public static void insertPadding(String text, int howManySpaces) {
        for(int i = 0; i < howManySpaces; i++) {
            System.out.print(" ");
        }
        System.out.println(text);
    }

    public static void waitForAnyKey() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        printCentered("Press any key to continue...");
        scanner.nextLine();
    }

    public static void lineGaps(int numberOfLines) {
        for(int i = 0; i < numberOfLines; i++) {
            System.out.println();
        }
    }

}
