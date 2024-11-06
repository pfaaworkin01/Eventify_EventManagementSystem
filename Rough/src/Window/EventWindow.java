package Window;

public class EventWindow implements Window {

    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        printCentered("EVENTIFY");
        printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        printCentered("Logged In\n");
        System.out.println(" ".repeat(65) + "1. Add New Event");
        System.out.println(" ".repeat(65) + "2. Cancel an Event");
        System.out.println(" ".repeat(65) + "3. Display Upcoming Events\n");
    }

    public static void printCentered(String text) {
        int terminalWidth = 150;
        int padding = (terminalWidth - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

}
