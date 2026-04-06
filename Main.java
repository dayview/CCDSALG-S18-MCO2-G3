import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input file path: ");
        String filePath = scanner.nextLine().trim();

        if (!FriendList.loadGraph(filePath)) {
            System.out.println("Failed to load the graph. Exiting.");
            return;
        }
        System.out.println("Graph loaded!");

        boolean running = true;
        while (running) {
            System.out.println("\nMAIN MENU");
            System.out.println("[1] Get friend list");
            System.out.println("[2] Get connection");
            System.out.println("[3] Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    FriendList.displayFriendList(scanner);
                    break;
                case "2":
                    ConnectionSearch.displayConnection(scanner);
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}