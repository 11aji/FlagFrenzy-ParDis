import java.util.List;

public class PerformanceAnalyzer {

    public static String getDetailedFeedback(int score, List<Flag> incorrectFlags) {
        StringBuilder feedback = new StringBuilder();
        feedback.append("Your Performance Analysis:\n\n");
        feedback.append("Score: ").append(score).append("/10\n\n");

        if (!incorrectFlags.isEmpty()) {
            feedback.append("Incorrect Flags:\n");
            // Use a set to avoid duplicates
            incorrectFlags.stream().distinct().forEach(flag -> feedback.append("- ").append(flag.getName()).append("\n"));
            feedback.append("\n");
        } else {
            feedback.append("Great job! You got all the flags correct.\n\n");
        }

        feedback.append("Tips for Improvement:\n");
        feedback.append("- Review the flags you got wrong and try to memorize them.\n");
        feedback.append("- Use online resources to study flags and their countries.\n");
        feedback.append("- Practice regularly to improve your recall speed.\n");

        return feedback.toString();
    }
}
