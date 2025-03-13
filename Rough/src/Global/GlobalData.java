package Global;

import AccessControl.Login;

public class GlobalData {

    public static boolean AUTHENTICATED = false;
    public static boolean PARTICIPANT_AUTHENTICATED = false;
    public static boolean BACK_TO_MAIN_MENU = false;
    public static String LOGGED_IN_USERNAME = "NULL";

    public static final String RESET_ANSI_ESCAPE_CODE_MODIFICATIONS = "\u001B[0m";
    public static final String BOLD_TEXT = "\u001B[1m";
    public static final String BLACK_TEXT = "\u001B[30m";
    public static final String RED_TEXT = "\u001B[31m";
    public static final String BLUE_TEXT = "\u001B[34m";
    public static final String YELLOW_TEXT = "\u001B[33m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String GREEN_TEXT = "\u001B[32m";

}
