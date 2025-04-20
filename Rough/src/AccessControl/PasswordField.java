package AccessControl;

import java.io.IOException;

public class PasswordField {
    public static String readPassword(String prompt) {
        System.out.print(prompt + " ");
        StringBuilder password = new StringBuilder();

        try {
            while (true) {
                char c = (char) System.in.read();

                if (c == '\n' || c == '\r') {
                    break;
                } else if (c == '\b' || c == 127) {
                    if (password.length() > 0) {
                        password.deleteCharAt(password.length() - 1);
                        System.out.print("\b \b");
                    }
                } else {
                    password.append(c);
                    //System.out.print("*"); // Print '*' instead of the character
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading password: " + e.getMessage());
        }

        return password.toString();
    }


}