import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class VirtualMazeGames {
    static class State {
        int x, y, keys, steps;

        State(int x, int y, int keys, int steps) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.steps = steps;
        }
    }

    public static int collectAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int allKeys = 0;

        // Find starting point and total number of keys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cell = grid[i].charAt(j);
                if (cell == 'S') {
                    queue.offer(new State(i, j, 0, 0));
                    visited.add(i + "," + j + ",0");
                } else if (cell >= 'a' && cell <= 'f') {
                    allKeys |= (1 << (cell - 'a'));
                }
            }
        }

        // Directions: up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Check if we've collected all keys
            if (current.keys == allKeys)
                return current.steps;

            for (int[] dir : directions) {
                int newX = current.x + dir[0], newY = current.y + dir[1];
                int newKeys = current.keys;
                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    char cell = grid[newX].charAt(newY);
                    // Check Wall
                    if (cell == 'W')
                        continue;
                    // Check Door & Key
                    if (cell >= 'A' && cell <= 'F' && (newKeys & (1 << (cell - 'A'))) == 0)
                        continue;
                    // Collect Key (If Found)
                    if (cell >= 'a' && cell <= 'f')
                        newKeys |= (1 << (cell - 'a'));

                    String newState = newX + "," + newY + "," + newKeys;
                    if (!visited.contains(newState)) {
                        visited.add(newState);
                        queue.offer(new State(newX, newY, newKeys, current.steps + 1));
                    }
                }
            }
        }

        // If we exit the loop, no solution was found
        return -1;
    }

    public static void main(String[] args) {
        String[] grid1 = { "SPaPP", "WWWPW", "bPAPB" };
        System.out.println("Minimum Steps: " + collectAllKeys(grid1));
    }
}
