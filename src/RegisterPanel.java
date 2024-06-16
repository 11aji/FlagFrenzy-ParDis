import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    private JTextField usernameField;
    private JButton registerButton;
    private JButton backButton;
    private UserManager userManager;
    private MainPanel mainPanel;

    public RegisterPanel(MainPanel mainPanel, UserManager userManager) {
        this.mainPanel = mainPanel;
        this.userManager = userManager;

        setLayout(new GridLayout(3, 1));

        usernameField = new JTextField();
        add(new JLabel("Username:"));
        add(usernameField);

        JPanel buttonPanel = new JPanel();
        registerButton = new JButton("Register");
        backButton = new JButton("Back");

        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        add(buttonPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.showStartPanel();
            }
        });
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
