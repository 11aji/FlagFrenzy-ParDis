import java.util.ArrayList;
import java.util.List;

public class FlagDatabase {
    private List<Flag> flags;

    public FlagDatabase() {
        flags = new ArrayList<>();
        loadFlags();
    }

    private void loadFlags() {
        // Placeholder for actual flag loading logic
        flags.add(new Flag("USA", "C:\\Users\\Elijah\\Documents\\School Files\\ParDist Project\\Flag Frenzy Proj\\src\\usa.png"));
        flags.add(new Flag("Canada", "C:\\Users\\Elijah\\Documents\\School Files\\ParDist Project\\Flag Frenzy Proj\\src\\canada.png"));
        // Add more flags as needed
    }

    public List<Flag> getFlags() {
        return flags;
    }

    public Flag getRandomFlag() {
        int index = (int) (Math.random() * flags.size());
        return flags.get(index);
    }
}
