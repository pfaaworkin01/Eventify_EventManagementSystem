import java.io.*;

public class credentialStorage {
    private static final String LOGIN_CREDENTIALS = "Login_Credentials.txt";

    public static void saveUser(User user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGIN_CREDENTIALS, true))) {
            writer.write(user + "\n");
        }
    }

    public static boolean checkCredentials(User user) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGIN_CREDENTIALS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (user.getUsername().equals(parts[0]) && user.getPassword().equals(parts[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
