package Window;

public class HomeWindow implements Window {

    @Override
    public void showWindow() {
        System.out.println("\n");
        System.out.println("*".repeat(147));
        Window.printCentered("EVENTIFY");
        Window.printCentered("Make Every Event Count");
        System.out.println("*".repeat(147));
        System.out.println("\n");
        Window.printCentered("HOME\n");
        System.out.println(" ".repeat(65) + "1. User Registration");
        System.out.println(" ".repeat(65) + "2. User Login");
        System.out.println(" ".repeat(65) + "3. Quit\n");
    }

    @Override
    public void takeInput() {}
}
