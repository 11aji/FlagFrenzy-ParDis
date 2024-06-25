import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private UserManager userManager;
    private User currentUser;

    public MainPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        userManager = new UserManager();

        LoginPanel loginPanel = new LoginPanel(this, userManager);
        GamePanel gamePanel = new GamePanel();

        cardPanel.add(loginPanel, "LOGIN");
        cardPanel.add(gamePanel, "GAME");

        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        cardLayout.show(cardPanel, "LOGIN");
    }

    public void showGamePanel() {
        cardLayout.show(cardPanel, "GAME");
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
