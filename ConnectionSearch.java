import java.util.*;

public class ConnectionSearch {

    public static void displayConnection(Scanner scanner) {
        System.out.print("Enter ID of first person: ");
        String input1 = scanner.nextLine().trim();
        System.out.print("Enter ID of second person: ");
        String input2 = scanner.nextLine().trim();

        int a, b;
        try {
            a = Integer.parseInt(input1);
            b = Integer.parseInt(input2);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid ID numbers.");
            return;
        }

        if (a < 0 || a >= FriendList.numAccounts || b < 0 || b >= FriendList.numAccounts) {
            System.out.println("Error: One or both ID numbers do not exist in the dataset.");
            return;
        }

        boolean[] visited = new boolean[FriendList.numAccounts];
        int[] parent = new int[FriendList.numAccounts];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();

        queue.add(a);
        visited[a] = true;
        boolean found = false;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == b) {
                found = true;
                break;
            }

            for (int friend : FriendList.adjList[current]) {
                if (!visited[friend]) {
                    visited[friend] = true;
                    parent[friend] = current;
                    queue.add(friend);
                }
            }
        }

        if (found) {
            System.out.println("There is a connection from " + a + " to " + b + "!");

            List<Integer> path = new ArrayList<>();
            int currNode = b;
            while (currNode != -1) {
                path.add(currNode);
                currNode = parent[currNode];
            }

            Collections.reverse(path);

            for (int i = 0; i < path.size() - 1; i++) {
                System.out.println(path.get(i) + " is friends with " + path.get(i + 1));
            }
        } else {
            System.out.println("Cannot find a connection between " + a + " and " + b);
        }
    }
}