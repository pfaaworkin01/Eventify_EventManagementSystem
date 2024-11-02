import java.util.Scanner;

public class UserService {
    private final Scanner scanner = new Scanner(System.in);

    public User promptUser() {
        System.out.println("Enter Username:");
        String username = scanner.nextLine();

        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        User user = new User(username, password);
        if (!user.isValid()) {
            System.out.println("Invalid Username or Password. Use only alphanumeric characters.");
            return null;
        }
        return user;
    }
}
