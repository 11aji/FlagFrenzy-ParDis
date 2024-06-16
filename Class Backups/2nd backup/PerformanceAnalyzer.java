public class PerformanceAnalyzer {
    public static String analyzePerformance(int score) {
        if (score >= 20) {
            return "Excellent! You have a great knowledge of national flags.";
        } else if (score >= 15) {
            return "Very good! You know many flags.";
        } else if (score >= 10) {
            return "Good job! You know quite a few flags, but there's room for improvement.";
        } else if (score >= 5) {
            return "Not bad! Keep practicing to improve your flag knowledge.";
        } else {
            return "Keep trying! The more you practice, the better you'll get.";
        }
    }

    public static String getDetailedFeedback(int score) {
        return "You scored " + score + " points. " + analyzePerformance(score) +
                " Here are some tips to improve: \n" +
                "1. Study flags regularly.\n" +
                "2. Take online quizzes.\n" +
                "3. Use flashcards to memorize flags.";
    }
}
