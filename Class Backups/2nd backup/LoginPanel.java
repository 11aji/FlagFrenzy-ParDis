import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JButton loginButton;
    private JButton registerButton;
    private UserManager userManager;
    private MainPanel mainPanel;

    public LoginPanel(MainPanel mainPanel, UserManager userManager) {
        this.mainPanel = mainPanel;
        this.userManager = userManager;

        setLayout(new GridLayout(3, 1));

        usernameField = new JTextField();
        add(new JLabel("Username:"));
        add(usernameField);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void loginUser() {
        String username = usernameField.getText().trim();
        User user = userManager.loginUser(username);
        if (user != null) {
            mainPanel.setCurrentUser(user);
            mainPanel.showDifficultyPanel();  // Show difficulty panel after login
        } else {
            JOptionPane.showMessageDialog(this, "User not found. Please register.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerUser() {
        String username = usernameField.getText().trim();
        if (userManager.registerUser(username)) {
            JOptionPane.showMessageDialog(this, "User registered successfully. Please login.", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
