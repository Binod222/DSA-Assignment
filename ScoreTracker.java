import java.util.ArrayList;
import java.util.List;

public class ScoreTracker {

    List<Double> scores = new ArrayList<>();

    // Add a score to the list of scores
    void addScore(Double score) {
        scores.add(score);
    }

    // Calculate the median score
    double getMedianScore() {
        scores.sort(null); // Sort the scores in ascending order

        // Determine the median based on the size of the score list
        switch (scores.size() % 2) {
            case 0:
                // If the size of the list is even, return the average of the two middle scores
                return (scores.get(scores.size() / 2) + scores.get(scores.size() / 2 - 1)) / 2;
            default:
                // If the size of the list is odd, return the middle score
                return scores.get(scores.size() / 2);
        }
    }

    public static void main(String[] args) {
        ScoreTracker sc = new ScoreTracker();
        sc.addScore(85.5);
        sc.addScore(92.3);
        sc.addScore(77.8);
        sc.addScore(90.1);
        double median1 = sc.getMedianScore();
        System.out.println("Median of first set of scores: " + median1); // Output the median of the first set of scores
        sc.addScore(81.2);

        sc.addScore(88.7);

        double median2 = sc.getMedianScore();
        System.out.println("Median of second set of scores: " + median2); // Output the median of the second set of scores
    }
}
