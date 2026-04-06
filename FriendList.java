import java.io.*;
import java.util.*;

public class FriendList {

    public static ArrayList<Integer>[] adjList;
    public static int numAccounts;
    public static int numFriendships;

    public static boolean loadGraph(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String[] nums = br.readLine().trim().split("\\s+");
            numAccounts = Integer.parseInt(nums[0]);
            numFriendships = Integer.parseInt(nums[1]);

            adjList = new ArrayList[numAccounts];
            for (int i = 0; i < numAccounts; i++)
                adjList[i] = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] edge = line.split("\\s+");
                int x = Integer.parseInt(edge[0]);
                int y = Integer.parseInt(edge[1]);
                adjList[x].add(y);
            }

            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found -> " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing file: " + e.getMessage());
        }
        return false;
    }

    public static void displayFriendList(Scanner scanner) {
        System.out.print("Enter ID of person: ");
        String input = scanner.nextLine().trim();

        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid ID.");
            return;
        }

        if (id < 0 || id >= numAccounts) {
            System.out.println("Error: ID#" + id + " does not exist in the dataset.");
            return;
        }

        ArrayList<Integer> friends = adjList[id];
        System.out.println("Person " + id + " has " + friends.size() + " friends!");

        if (friends.isEmpty()) {
            System.out.println("List of friends: (none)");
        } else {
            StringBuilder sb = new StringBuilder("List of friends:");
            for (int friend : friends)
                sb.append(" ").append(friend);
            System.out.println(sb.toString());
        }
    }
}