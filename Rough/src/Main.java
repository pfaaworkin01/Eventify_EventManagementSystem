import AccessControl.Login;
import AccessControl.Register;
import GlobalData.GlobalData;
import Window.HomeWindow;
import Window.LoggedInWindow;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HomeWindow homeWindow = new HomeWindow();
        homeWindow.takeInput();
    }

}