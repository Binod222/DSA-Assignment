import java.util.Arrays;

/*
 * You are given an integer n representing the total number of individuals. Each individual is identified by a unique 
 * ID from 0 to n-1. The individuals have a unique secret that they can share with others.
 * 
 * The secret-sharing process begins with person 0, who initially possesses the secret. Person 0 can share the secret 
 * with any number of individuals simultaneously during specific time intervals. Each time interval is represented by 
 * a tuple (start, end) where start and end are non-negative integers indicating the start and end times of the interval.
 * 
 * You need to determine the set of individuals who will eventually know the secret after all the possible secret-sharing
 * intervals have occurred
*/

public class SecretSharing {
    int[] individuals;
    int[][] intervals;

    // Constructor
    SecretSharing(int noOfIndividuals, int[][] intervalMatrix) {
        individuals = new int[noOfIndividuals];
        for (int i = 0; i < noOfIndividuals; i++) {
            individuals[i] = i;
        }
        intervals = intervalMatrix;
    }

    // Method to determine the set of individuals who will eventually know the secret
    int[] startSharing() {
        int secretKnowingIndividuals = 0;

        // Iterate through each interval
        for (int[] interval : intervals) {
            // Count the number of individuals who will know the secret during this interval
            secretKnowingIndividuals += interval[1] - interval[0] + 1;

            // If all individuals know the secret, break the loop
            if (secretKnowingIndividuals >= individuals.length) {
                break;
            }
        }

        // Return an array of secret knowing individuals
        return Arrays.copyOf(individuals, secretKnowingIndividuals);
    }

    // Main method for testing
    public static void main(String[] args) {
        SecretSharing share = new SecretSharing(5, new int[][] { { 0, 1 }, { 1, 3 }, { 2, 4 } });
        int[] secretKnowingIndividuals = share.startSharing();
        for (int i : secretKnowingIndividuals) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
