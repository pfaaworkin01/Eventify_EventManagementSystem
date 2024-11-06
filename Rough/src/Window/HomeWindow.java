package Window;

public class HomeWindow implements Window {

    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        printCentered("EVENTIFY");
        printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        printCentered("HOME\n");
        System.out.println(" ".repeat(65) + "1. User Registration");
        System.out.println(" ".repeat(65) + "2. User Login");
        System.out.println(" ".repeat(65) + "3. Quit\n");
    }

    public static void printCentered(String text) {
        int terminalWidth = 150;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }
}
