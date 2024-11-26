package Global;

public class GlobalMethod {

    public static void printCentered(String text) {
        int terminalWidth = 150;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

}
