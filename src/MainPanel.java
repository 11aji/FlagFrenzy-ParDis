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

        StartPanel startPanel = new StartPanel(this);
        LoginPanel loginPanel = new LoginPanel(this, userManager);
        RegisterPanel registerPanel = new RegisterPanel(this, userManager);
        DifficultyPanel difficultyPanel = new DifficultyPanel(this);
        GamePanel gamePanel = new GamePanel(this);  // Pass the MainPanel reference here

        cardPanel.add(startPanel, "START");
        cardPanel.add(loginPanel, "LOGIN");
        cardPanel.add(registerPanel, "REGISTER");
        cardPanel.add(difficultyPanel, "DIFFICULTY");
        cardPanel.add(gamePanel, "GAME");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "START");
    }

    public void showStartPanel() {
        cardLayout.show(cardPanel, "START");
    }

    public void showDifficultyPanel() {
        cardLayout.show(cardPanel, "DIFFICULTY");
    }

    public void showGamePanel(String difficulty) {
        ((GamePanel) cardPanel.getComponent(4)).startGame(difficulty);
        cardLayout.show(cardPanel, "GAME");
    }

    public void showLoginPanel() {
        cardLayout.show(cardPanel, "LOGIN");
    }

    public void showRegisterPanel() {
        cardLayout.show(cardPanel, "REGISTER");
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
