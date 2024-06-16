import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private UserManager userManager;
    private User currentUser;
    private boolean mediumUnlocked = false;
    private boolean hardUnlocked = false;

    public MainPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        userManager = new UserManager();

        LoginPanel loginPanel = new LoginPanel(this, userManager);
        DifficultyPanel difficultyPanel = new DifficultyPanel(this);
        GamePanel gamePanel = new GamePanel(this);  // Pass the MainPanel reference here

        cardPanel.add(loginPanel, "LOGIN");
        cardPanel.add(difficultyPanel, "DIFFICULTY");
        cardPanel.add(gamePanel, "GAME");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "LOGIN");
    }

    public void showDifficultyPanel() {
        cardLayout.show(cardPanel, "DIFFICULTY");
    }

    public void showGamePanel(String difficulty) {
        ((GamePanel) cardPanel.getComponent(2)).startGame(difficulty);
        cardLayout.show(cardPanel, "GAME");
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void unlockMedium() {
        mediumUnlocked = true;
    }

    public void unlockHard() {
        hardUnlocked = true;
    }

    public boolean isMediumUnlocked() {
        return mediumUnlocked;
    }

    public boolean isHardUnlocked() {
        return hardUnlocked;
    }
}
