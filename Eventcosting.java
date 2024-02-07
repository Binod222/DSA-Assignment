import java.util.Scanner;

public class Eventcosting {
    int costMatrix[][];

    // Method to insert cost matrix based on user input
    int[][] insertCostMatrix(int venues, int themes) {
        costMatrix = new int[venues][themes];
        Scanner scanner = new Scanner(System.in);
        for (int venue = 0; venue < venues; venue++) {
            for (int theme = 0; theme < themes; theme++) {
                System.out.print("Enter cost for venue " + venue + " and theme " + theme + ": ");
                costMatrix[venue][theme] = scanner.nextInt();
            }
        }
        scanner.close();
        return costMatrix;
    }

    // Method to print the cost matrix
    void printCostMatrix(int[][] matrix) {
        System.out.println("\nCost Matrix:");
        for (int[] row : matrix) {
            for (int cost : row) {
                System.out.print(cost + "\t");
            }
            System.out.println();
        }
    }

    // Method to calculate minimum cost of events and return details
    String[] calculateMinimumCost(int[][] costMatrix) {
        StringBuilder selectedVenuesAndThemes = new StringBuilder(); // Stores selected venues and themes
        int previousTheme = 0; // Stores the previous theme to avoid repeating the same theme
        int totalCost = 0; // Stores the total cost of events

        for (int venue = 0; venue < costMatrix.length; venue++) {
            int smallestCost = costMatrix[venue][0]; // Initialize smallestCost with the first theme cost
            selectedVenuesAndThemes.append(venue);
            selectedVenuesAndThemes.append(" ");
            selectedVenuesAndThemes.append(0);

            for (int theme = 1; theme < costMatrix[0].length; theme++) {
                if (venue == 0) {
                    smallestCost = costMatrix[venue][theme];
                    selectedVenuesAndThemes.replace(selectedVenuesAndThemes.length() - 1, selectedVenuesAndThemes.length(), Integer.toString(theme));
                    previousTheme = theme;
                    continue;
                } else if (costMatrix[venue][theme] < smallestCost && previousTheme != theme) {
                    smallestCost = costMatrix[venue][theme];
                    previousTheme = theme;
                }
            }

            selectedVenuesAndThemes.append(" + \t");
            totalCost += smallestCost;
        }
        return new String[] { Integer.toString(totalCost), selectedVenuesAndThemes.toString() };
    }

    public static void main(String[] args) {
        Eventcosting event = new Eventcosting();
        event.insertCostMatrix(3, 3);
        event.printCostMatrix(event.costMatrix);
        System.out.println("\nThe minimum cost of events is:");
        String[] result = event.calculateMinimumCost(event.costMatrix);
        System.out.println(result[0]);
        System.out.println("And it's made from:");
        System.out.println(result[1]);
    }
}
