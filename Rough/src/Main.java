import AccessControl.Login;
import AccessControl.Register;
import Team.TeamManager;
import java.io.Serializable;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        Login login = new Login();
        Register register = new Register();
        TeamManager teamManager = new TeamManager();

        while (!exit) {
            System.out.println("\n");
            System.out.println("\t\tSelect an Option:\n");
            System.out.println("\t\t1. Register");
            System.out.println("\t\t2. Login");
            System.out.println("\t\t3. Manage Teams");
            System.out.println("\t\t4. Exit");
            System.out.println("\n");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    register.register();
                    break;
                case "2":
                    login.login();
                    break;
                case "3":
                    teamManager.manageTeams();
                    break;
                case "4":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }

        scanner.close();
    }

}