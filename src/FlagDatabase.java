import java.util.ArrayList;
import java.util.List;

public class FlagDatabase {
    private List<Flag> easyFlags, mediumFlags, hardFlags;

    public FlagDatabase() {
        easyFlags = new ArrayList<>();
        mediumFlags = new ArrayList<>();
        hardFlags = new ArrayList<>();
        loadFlags();
    }

    private void loadFlags() {
        // Placeholder for actual flag loading logic
        // Easy flags
        easyFlags.add(new Flag("USA", "path/to/usa_flag.png"));
        easyFlags.add(new Flag("Canada", "path/to/canada_flag.png"));

        // Add more easy flags

        // Medium flags
        mediumFlags.add(new Flag("France", "path/to/france_flag.png"));
        mediumFlags.add(new Flag("Germany", "path/to/germany_flag.png"));
        // Add more medium flags

        // Hard flags
        hardFlags.add(new Flag("Bhutan", "path/to/bhutan_flag.png"));
        hardFlags.add(new Flag("Kazakhstan", "path/to/kazakhstan_flag.png"));
        // Add more hard flags
    }


    public List<Flag> getFlags(String difficulty) {
        switch (difficulty) {
            case "Medium":
                return mediumFlags;
            case "Hard":
                return hardFlags;
            case "Easy":
            default:
                return easyFlags;
        }
    }
}
