import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private UserManager userManager;
    private User currentUser;
    private boolean mediumUnlocked = false;
    private boolean hardUnlocked = false;
    private GamePanel gamePanel;
    private SoundEffect mainMusic;
    private SoundEffect gameplayMusic;

    public MainPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        userManager = new UserManager();

        TitlePanel titlePanel = new TitlePanel(this);
        StartPanel startPanel = new StartPanel(this);
        LoginPanel loginPanel = new LoginPanel(this, userManager);
        RegisterPanel registerPanel = new RegisterPanel(this, userManager);
        DifficultyPanel difficultyPanel = new DifficultyPanel(this);
        gamePanel = new GamePanel(this);

        cardPanel.add(titlePanel, "TITLE");
        cardPanel.add(startPanel, "START");
        cardPanel.add(loginPanel, "LOGIN");
        cardPanel.add(registerPanel, "REGISTER");
        cardPanel.add(difficultyPanel, "DIFFICULTY");
        cardPanel.add(gamePanel, "GAME");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "TITLE");

        // Load background music
        mainMusic = new SoundEffect("src/main_music.wav");
        gameplayMusic = new SoundEffect("src/gameplay_music.wav");

        // Start playing main music when the application starts
        mainMusic.loop();

        // Ensure user progress is saved when the application exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (currentUser != null) {
                userManager.updateUserProgress(currentUser);
            }
        }));
    }

    public void showStartPanel() {
        cardLayout.show(cardPanel, "START");
    }

    public void showDifficultyPanel() {
        cardLayout.show(cardPanel, "DIFFICULTY");
    }

    public void showGamePanel(String difficulty) {
        gamePanel.startGame(difficulty);
        cardLayout.show(cardPanel, "GAME");
        playGameplayMusic();
    }

    public void showLoginPanel() {
        cardLayout.show(cardPanel, "LOGIN");
    }

    public void showRegisterPanel() {
        cardLayout.show(cardPanel, "REGISTER");
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        this.mediumUnlocked = user.isMediumUnlocked();
        this.hardUnlocked = user.isHardUnlocked();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void unlockMedium() {
        mediumUnlocked = true;
        currentUser.setMediumUnlocked(true);
        userManager.updateUserProgress(currentUser);
    }

    public void unlockHard() {
        hardUnlocked = true;
        currentUser.setHardUnlocked(true);
        userManager.updateUserProgress(currentUser);
    }

    public boolean isMediumUnlocked() {
        return mediumUnlocked;
    }

    public boolean isHardUnlocked() {
        return hardUnlocked;
    }

    public void playMainMusic() {
        gameplayMusic.stop();
        if (!mainMusic.isPlaying()) {
            mainMusic.loop();
        }
    }

    public void playGameplayMusic() {
        mainMusic.stop();
        gameplayMusic.loop();
    }
}
