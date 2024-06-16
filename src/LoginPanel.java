import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JButton loginButton;
    private JButton backButton;
    private UserManager userManager;
    private MainPanel mainPanel;

    public LoginPanel(MainPanel mainPanel, UserManager userManager) {
        this.mainPanel = mainPanel;
        this.userManager = userManager;

        setLayout(new GridLayout(3, 1));

        usernameField = new JTextField();
        add(new JLabel("Username:"));
        add(usernameField);

        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        add(buttonPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.showStartPanel();
            }
        });
    }

    private void loginUser() {
        String username = usernameField.getText().trim();
        User user = userManager.loginUser(username);
        if (user != null) {
            mainPanel.setCurrentUser(user);
            mainPanel.showDifficultyPanel();
        } else {
            JOptionPane.showMessageDialog(this, "User not found. Please register.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
